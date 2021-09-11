package com.example.mysqlschemasync.service.impl;

import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.ColumnsMapper;
import com.example.mysqlschemasync.mapper.StatisticsMapper;
import com.example.mysqlschemasync.model.*;
import com.example.mysqlschemasync.service.SyncService;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

        // 1. 去 src 找到对应的 实例.数据库.表 获取字段+索引
        Set<ColumnsDo> srcColumns = DaoFacade.ofMapper(srcConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        Set<StatisticsDo> srcStatistics = DaoFacade.ofMapper(srcConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));

        // 2. 去 dst 找到对应的 实例.数据库.表 获取字段+索引
        Set<ColumnsDo> dstColumns = DaoFacade.ofMapper(dstConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        Set<StatisticsDo> dstStatistics = DaoFacade.ofMapper(dstConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));

        // 3. diff 差异. 字段不一致,索引不一致
        Set<ColumnsDo> diffColumn = diffColumn(srcColumns, dstColumns);
        Set<StatisticsDo> diffStatistics = diffStatistics(srcStatistics, dstStatistics);

        // 调试信息
        diffColumn.forEach(col -> {
            LOGGER.info("不同的行信息为:{}\n", col.toString());
        });

        diffStatistics.forEach(statistics -> {
            LOGGER.info("不同的索引信息为:{}\n", statistics.toString());
        });


        //  4. 基于差异, 生成 sql
        List<String> columnSql = getColumnSql(diffColumn);
        List<String> statisticsSql = getStatisticsSql(diffStatistics);

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
    private Set<ColumnsDo> diffColumn(Set<ColumnsDo> srcColumns, Set<ColumnsDo> dstColumns) {
        // 对比 src 和 dst,不管是长度还是注释还是默认值不一致,只要存在这些情况就以 src 为准,生成 sql 执行语句
        return Sets.difference(srcColumns, dstColumns).immutableCopy();
    }


    private Set<StatisticsDo> diffStatistics(Set<StatisticsDo> srcStatistics, Set<StatisticsDo> dstStatistics) {

        return Sets.difference(srcStatistics, dstStatistics).immutableCopy();
    }

    /**
     * 如何可以快速解决?可不可以根据对象转成 sql
     *
     * @param diffColumn
     * @return
     */
    private List<String> getColumnSql(Set<ColumnsDo> diffColumn) {

        // 添加列：alter table 表名 add column 列名 varchar(30);
        // 修改列名MySQL： alter table bbb change nnnnn hh int;
        // 修改列属性：alter table t_book modify name varchar(22);
        diffColumn.stream().map(column -> column.getColumnName());
        return null;
    }

    /**
     * 生成 sql 的话,联合索引比较麻烦,是存为两行,而且索引不一定是加,也有可能是修改字段或者修改备注
     *
     * @param diffStatistics
     * @return
     */
    private List<String> getStatisticsSql(Set<StatisticsDo> diffStatistics) {
        return null;
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
