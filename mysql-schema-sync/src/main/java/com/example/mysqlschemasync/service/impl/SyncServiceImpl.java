package com.example.mysqlschemasync.service.impl;

import com.example.mysqlschemasync.constants.SqlFormatterConst;
import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.ColumnsMapper;
import com.example.mysqlschemasync.mapper.StatisticsMapper;
import com.example.mysqlschemasync.model.*;
import com.example.mysqlschemasync.service.SyncService;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.ibatis.javassist.compiler.ast.StringL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.text.resources.cldr.ti.FormatData_ti_ER;

import javax.xml.ws.EndpointReference;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Sync
 * @Description
 * @date 2021/8/29 11:40
 */
@Service
public class SyncServiceImpl implements SyncService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncServiceImpl.class);

    @Override
    public void doSyncInstance(SyncInstaceRequest syncInfo) {
    }

    @Override
    public void doSyncDatabse(SyncDatabaseRequest syncInfo) {

    }


    @Override
    public void doSyncTable(SyncTableRequest syncInfo) {
        // 信息获取,后面会删除的
        ConnectInfo srcConnectInfo = syncInfo.getSrcConnectInfo();
        ConnectInfo dstConnectInfo = syncInfo.getDstConnectInfo();
        String dbName = syncInfo.getDbName();
        String tableName = syncInfo.getTableName();

        // 1. 去 src 找到对应的 实例.数据库.表 获取字段+索引(获取索引和列的字段可以用 druid 进行获取，到时看看能不能优化)
        Set<ColumnsDo> srcColumns = DaoFacade.ofMapper(srcConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        Set<StatisticsDo> srcStatistics = DaoFacade.ofMapper(srcConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));

        // 2. 去 dst 找到对应的 实例.数据库.表 获取字段+索引
        Set<ColumnsDo> dstColumns = DaoFacade.ofMapper(dstConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        Set<StatisticsDo> dstStatistics = DaoFacade.ofMapper(dstConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));

        // 3. diff 差异. 字段不一致,索引不一致
        HashMap<String, Set<ColumnsDo>> diffColumn = diffColumn(srcColumns, dstColumns);
        Set<StatisticsDo> diffStatistics = diffStatistics(srcStatistics, dstStatistics);

        // 调试信息
        diffColumn.get(SqlFormatterConst.MODIFY_COLUMN).forEach(col -> {
            LOGGER.info("需要进行修改的列:{}\n", col.toString());
        });
        diffColumn.get(SqlFormatterConst.ADD_COLUMN).forEach(col -> {
            LOGGER.info("需要进行新增的列:{}\n", col.toString());
        });
        //
        // diffStatistics.forEach(statistics -> {
        //     LOGGER.info("不同的索引信息为:{}\n", statistics.toString());
        // });


        //  4. 基于差异, 生成 sql
        List<String> columnSql = getColumnSql(diffColumn);
        columnSql.forEach(col -> {
            LOGGER.info("拼接的 sql 语句：{}", col);
        });
        List<String> statisticsSql = getStatisticsSql(diffStatistics, dstStatistics);

        //  5. 执行 sql
        executeSql(columnSql);
        executeSql(statisticsSql);

    }


    /**
     * 关于 diff 的部分,是不是不需要重复造轮子?可以看看 https://javers.org/
     *
     * @param srcColumns
     * @param dstColumns
     * @return
     */
    private HashMap<String, Set<ColumnsDo>> diffColumn(Set<ColumnsDo> srcColumns, Set<ColumnsDo> dstColumns) {
        // 对比 src 和 dst,不管是长度还是注释还是默认值不一致,只要存在这些情况就以 src 为准,生成 sql 执行语句
        // 判断一下字段名,src有 dst 没有的,则为需要插入的 column,否则为需要 modify 的
        HashMap<String, Set<ColumnsDo>> columnsMap = new HashMap<>();
        Set<ColumnsDo> diffColumnsDos = Sets.difference(srcColumns, dstColumns).immutableCopy();
        // 差异的所有行名字
        Set<String> diffColumnsName = diffColumnsDos.stream().map(ColumnsDo::getColumnName).collect(Collectors.toSet());
        Set<String> dstColumnsName = dstColumns.stream().map(ColumnsDo::getColumnName).collect(Collectors.toSet());

        // 目标数据库的行包含在了差异的行列表内,则为需要修改的
        Set<ColumnsDo> modifyColumns = diffColumnsDos.stream().filter(diffColumnName -> dstColumnsName.contains(diffColumnName.getColumnName())).collect(Collectors.toSet());
        // 目标数据库的行不包含在差异的列表内,则为新增
        Set<ColumnsDo> addColumns = diffColumnsDos.stream().filter(diffColumnName -> !dstColumnsName.contains(diffColumnName.getColumnName())).collect(Collectors.toSet());
        columnsMap.put(SqlFormatterConst.MODIFY_COLUMN, modifyColumns);
        columnsMap.put(SqlFormatterConst.ADD_COLUMN, addColumns);
        return columnsMap;
    }


    private Set<StatisticsDo> diffStatistics(Set<StatisticsDo> srcStatistics, Set<StatisticsDo> dstStatistics) {

        return Sets.difference(srcStatistics, dstStatistics).immutableCopy();
    }

    /**
     * 根据有差异的列以及目标列,生成列的相关 sql
     * 思路是:先将差异的列去dst内查一下，假设列名存在则需要 modify,不存在说明需要 add
     *
     * @param diffColumn
     * @return
     */
    private List<String> getColumnSql(HashMap<String, Set<ColumnsDo>> diffColumn) {

        List<String> columnSql = Lists.newArrayList();
        Set<Map.Entry<String, Set<ColumnsDo>>> entries = diffColumn.entrySet();
        for (Map.Entry<String, Set<ColumnsDo>> entry : entries) {
            if (SqlFormatterConst.ADD_COLUMN.equals(entry.getKey())) {
                List<String> stringList = entry.getValue().stream().map(col -> getColumnFormate(entry.getKey(), col)).collect(Collectors.toList());
                columnSql.addAll(stringList);
            } else if (SqlFormatterConst.MODIFY_COLUMN.equals(entry.getKey())) {
                List<String> stringList = entry.getValue().stream().map(col -> getColumnFormate(entry.getKey(), col)).collect(Collectors.toList());
                columnSql.addAll(stringList);
            }
        }
        return columnSql;

    }

    /**
     * 生成 sql 的话,联合索引比较麻烦,是存为两行,而且索引不一定是加,也有可能是修改字段或者修改备注
     *
     * @param diffStatistics
     * @return
     */
    private List<String> getStatisticsSql(Set<StatisticsDo> diffStatistics, Set<StatisticsDo> Statistics) {
        return null;
    }


    public String getColumnFormate(String formatter, ColumnsDo column) {
        LOGGER.info(formatter);
        LOGGER.info(column.getColumnDefault());

        return formatter.
                replace("{schemaName}", column.getTableSchema()).
                replace("{tableName}", column.getTableName()).
                replace("{columnName}", column.getColumnName()).
                replace("{columnDefault}", column.getColumnDefault()).
                replace("{columnType}", column.getColumnType()).
                replace("{isNullable}", "NO".equals(column.getIsNullable()) ? "not null" : "can be null").
                replace("{columnComment}", column.getColumnComment());
    }

    private void executeSql(List<String> sqls) {
    }

    /**
     * 多态的方式
     * 如果Controller层不校验是同步实例级别还是库级别还是表级别, 就需要在这一层判断.
     * 那么这里考虑多态么?
     * 有表名代表是表级别,
     * 有库名代表库级别,
     * 两个都没代表实例级别
     *
     * @param syncInfo
     */
    @Override
    public void doSync(SyncInfo syncInfo) {

        syncInfo.getSrcConnectInfo();
        syncInfo.getDstConnectInfo();

    }
}
