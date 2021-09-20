package com.example.mysqlschemasync.service.impl;

import com.example.mysqlschemasync.constants.SqlFormatterConst;
import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.ColumnsMapper;
import com.example.mysqlschemasync.mapper.StatisticsMapper;
import com.example.mysqlschemasync.model.*;
import com.example.mysqlschemasync.service.SyncService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
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

        syncColumn(srcConnectInfo, dstConnectInfo, dbName, tableName);
        syncStatistics(srcConnectInfo, dstConnectInfo, dbName, tableName);
    }

    public void syncColumn(ConnectInfo srcConnectInfo, ConnectInfo dstConnectInfo, String dbName, String tableName) {
        // 1. 去 src 找到对应的 实例.数据库.表 获取字段+索引(获取索引和列的字段可以用 druid 进行获取，到时看看能不能优化)
        Set<ColumnsDo> srcColumns = DaoFacade.ofMapper(srcConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        // 2. 去 dst 找到对应的 实例.数据库.表 获取字段+索引
        Set<ColumnsDo> dstColumns = DaoFacade.ofMapper(dstConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        // 3. diff 差异. 字段不一致,索引不一致
        HashMap<String, Set<ColumnsDo>> diffColumn = diffColumn(srcColumns, dstColumns);
        //  4. 基于差异, 生成 sql
        List<String> columnSql = getColumnSql(diffColumn);

        // 打印调试信息
        columnSql.forEach(col -> {
            LOGGER.info("拼接的列 sql 语句：{}", col);
        });

        //  5. 执行 sql
        // executeSql(dstConnectInfo, columnSql);

    }

    public void syncStatistics(ConnectInfo srcConnectInfo, ConnectInfo dstConnectInfo, String dbName, String tableName) {

        Set<StatisticsDo> srcStatistics = DaoFacade.ofMapper(srcConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTableGroupBy(dbName, tableName));
        Set<StatisticsDo> dstStatistics = DaoFacade.ofMapper(dstConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTableGroupBy(dbName, tableName));
        HashMap<String, Set<StatisticsDo>> diffStatistics = diffStatistics(srcStatistics, dstStatistics);
        HashMap<String, Set<StatisticsDo>> diffStatistics2 = diffStatisticsWithGroupBy(srcStatistics, dstStatistics);
        List<String> statisticsSql = getStatisticsSql(diffStatistics);
        List<String> statisticsSql2 = getStatisticsSql(diffStatistics2);
        statisticsSql.forEach(statis -> {
            LOGGER.info("拼接的索引 sql 语句方式1：{}", statis);
        });

        statisticsSql2.forEach(statis -> {
            LOGGER.info("拼接的索引 sql 语句方式2：{}", statis);
        });

        // executeSql(dstConnectInfo, statisticsSql);

    }

    public void syncStatisticsWithGroupBy(ConnectInfo srcConnectInfo, ConnectInfo dstConnectInfo, String dbName, String tableName) {

        Set<StatisticsDo> srcStatistics = DaoFacade.ofMapper(srcConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));
        Set<StatisticsDo> dstStatistics = DaoFacade.ofMapper(dstConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));
        HashMap<String, Set<StatisticsDo>> diffStatistics = diffStatistics(srcStatistics, dstStatistics);
        List<String> statisticsSql = getStatisticsSql(diffStatistics);
        statisticsSql.forEach(statis -> {
            LOGGER.info("拼接的索引 sql 语句：{}", statis);
        });

        executeSql(dstConnectInfo, statisticsSql);

    }


    /**
     * 关于 diff 的部分,是不是不需要重复造轮子?可以看看 https://javers.org/
     * hashmap 分成两份, column 比较简单就是需要将 add 和 modify 的 sql 变成 key , set(do) 变成 value
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

    /**
     * 结构跟上面一致,区别在于组合索引的话需要进行组合
     * 其实有另一种解决方案就是利用 stream 的 groupby,有时间研究一下
     *
     * @param srcStatistics
     * @param dstStatistics
     * @return
     */
    private HashMap<String, Set<StatisticsDo>> diffStatistics(Set<StatisticsDo> srcStatistics, Set<StatisticsDo> dstStatistics) {

        HashMap<String, Set<StatisticsDo>> statisticsMap = new HashMap<>();
        Set<StatisticsDo> diffStatisticsDos = Sets.difference(srcStatistics, dstStatistics).immutableCopy();

        Set<StatisticsDo> modifyFullText = diffStatisticsDos.stream().filter(diffStatis -> ("FULLTEXT".equals(diffStatis.getIndexType()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyPrimaryKey = diffStatisticsDos.stream().filter(diffStatis -> (0L == (diffStatis.getNonUnique()) && "PRIMARY".equals(diffStatis.getIndexName()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyUniqueKey = diffStatisticsDos.stream().filter(diffStatis -> (0L == (diffStatis.getNonUnique()) && !"PRIMARY".equals(diffStatis.getIndexName()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyIndex = diffStatisticsDos.stream().filter(diffStatis -> (1L == (diffStatis.getNonUnique()) && (!"FULLTEXT".equals(diffStatis.getIndexType())))).collect(Collectors.toSet());


        statisticsMap.put(SqlFormatterConst.MODIFY_PRIMARY_KEY, modifyPrimaryKey);
        statisticsMap.put(SqlFormatterConst.MODIFY_UNIQUE_INDEX, modifyUniqueKey);
        statisticsMap.put(SqlFormatterConst.MODIFY_FULLTEXT, modifyFullText);
        statisticsMap.put(SqlFormatterConst.MODIFY_INDEX, modifyIndex);

        return statisticsMap;
    }

    private HashMap<String, Set<StatisticsDo>> diffStatisticsWithGroupBy(Set<StatisticsDo> srcStatistics, Set<StatisticsDo> dstStatistics) {

        HashMap<String, Set<StatisticsDo>> statisticsMap = new HashMap<>();
        Set<StatisticsDo> diffStatisticsDos = Sets.difference(getStatisDos(srcStatistics), getStatisDos(dstStatistics)).immutableCopy();

        Set<StatisticsDo> modifyFullText = diffStatisticsDos.stream().filter(diffStatis -> ("FULLTEXT".equals(diffStatis.getIndexType()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyPrimaryKey = diffStatisticsDos.stream().filter(diffStatis -> (0L == (diffStatis.getNonUnique()) && "PRIMARY".equals(diffStatis.getIndexName()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyUniqueKey = diffStatisticsDos.stream().filter(diffStatis -> (0L == (diffStatis.getNonUnique()) && !"PRIMARY".equals(diffStatis.getIndexName()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyIndex = diffStatisticsDos.stream().filter(diffStatis -> (1L == (diffStatis.getNonUnique()) && (!"FULLTEXT".equals(diffStatis.getIndexType())))).collect(Collectors.toSet());


        statisticsMap.put(SqlFormatterConst.MODIFY_PRIMARY_KEY, modifyPrimaryKey);
        statisticsMap.put(SqlFormatterConst.MODIFY_UNIQUE_INDEX, modifyUniqueKey);
        statisticsMap.put(SqlFormatterConst.MODIFY_FULLTEXT, modifyFullText);
        statisticsMap.put(SqlFormatterConst.MODIFY_INDEX, modifyIndex);

        return statisticsMap;
    }

    private Set<StatisticsDo> getStatisDos(Set<StatisticsDo> Statistics) {
        Set<StatisticsDo> collect = Statistics.stream().
                // peek(str -> System.out.println("初始的索引" + str.getIndexName() + ":" + str.toString())).
                        collect(Collectors.groupingBy(StatisticsDo::getIndexName)).
                        entrySet().
                        stream().
                // peek(streamsres -> System.out.println("每一个单独的索引进行entry" + streamsres)).
                        map(getSingleColumn -> {
                    List<String> columns = new ArrayList<>();
                    List<Long> seq = new ArrayList<>();

                    getSingleColumn.getValue().stream().sorted(Comparator.comparingInt(o -> o.getSeqInIndex().intValue())).forEach(st -> {
                        columns.add(st.getColumnName());
                        seq.add(st.getSeqInIndex());
                    });
                    getSingleColumn.getValue().get(0).setColumnName(String.join(",", columns));
                    getSingleColumn.getValue().get(0).setSeqInIndex(seq.get(seq.size() - 1));
                    return getSingleColumn.getValue().get(0);
                }).collect(Collectors.toSet());
        return collect;
    }


    private HashMap<String, Set<StatisticsDo>> diffStatisticsGroupBy(Set<StatisticsDo> srcStatistics, Set<StatisticsDo> dstStatistics) {

        HashMap<String, Set<StatisticsDo>> statisticsMap = new HashMap<>();
        // 这里有可能会出现组合索引怎么个解决办法？其实从源头就应该解决这个问题,判断重复就得将组合的给进行diff
        Set<StatisticsDo> diffStatisticsDos = Sets.difference(srcStatistics, dstStatistics).immutableCopy();

        Set<StatisticsDo> modifyFullText = diffStatisticsDos.stream().filter(diffStatis -> ("FULLTEXT".equals(diffStatis.getIndexType()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyPrimaryKey = diffStatisticsDos.stream().filter(diffStatis -> (0L == (diffStatis.getNonUnique()) && "PRIMARY".equals(diffStatis.getIndexName()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyUniqueKey = diffStatisticsDos.stream().filter(diffStatis -> (0L == (diffStatis.getNonUnique()) && !"PRIMARY".equals(diffStatis.getIndexName()))).collect(Collectors.toSet());
        Set<StatisticsDo> modifyIndex = diffStatisticsDos.stream().filter(diffStatis -> (1L == (diffStatis.getNonUnique()) && (!"FULLTEXT".equals(diffStatis.getIndexType())))).collect(Collectors.toSet());

        statisticsMap.put(SqlFormatterConst.MODIFY_PRIMARY_KEY, modifyPrimaryKey);
        statisticsMap.put(SqlFormatterConst.MODIFY_UNIQUE_INDEX, modifyUniqueKey);
        statisticsMap.put(SqlFormatterConst.MODIFY_FULLTEXT, modifyFullText);
        statisticsMap.put(SqlFormatterConst.MODIFY_INDEX, modifyIndex);

        return statisticsMap;
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
            List<String> stringList = entry.getValue().stream().map(col -> getSqlFormatter(entry.getKey(), col)).collect(Collectors.toList());
            columnSql.addAll(stringList);

        }
        return columnSql;
    }

    /**
     * 生成 sql 的话,联合索引比较麻烦,是存为两行,而且索引不一定是加,也有可能是修改字段或者修改备注
     *
     * @param diffStatistics
     * @return
     */
    private List<String> getStatisticsSql(HashMap<String, Set<StatisticsDo>> diffStatistics) {
        List<String> staticsSql = new ArrayList<>();
        Set<Map.Entry<String, Set<StatisticsDo>>> entries = diffStatistics.entrySet();

        for (Map.Entry<String, Set<StatisticsDo>> entry : entries) {
            List<String> stringList = entry.getValue().stream().map(statis -> getSqlFormatter(entry.getKey(), statis)).collect(Collectors.toList());
            staticsSql.addAll(stringList);
        }

        return staticsSql;
    }


    public String getSqlFormatter(String formatter, ColumnsDo column) {
        return formatter.
                replace("{schemaName}", column.getTableSchema()).
                replace("{tableName}", column.getTableName()).
                replace("{columnName}", column.getColumnName()).
                replace("{columnDefault}", column.getColumnDefault()).
                replace("{columnType}", column.getColumnType()).
                replace("{isNullable}", "NO".equals(column.getIsNullable()) ? "not null" : "can be null").
                replace("{columnComment}", column.getColumnComment());
    }

    public String getSqlFormatter(String formatter, StatisticsDo statisticsDo) {
        return formatter.
                replace("{schemaName}", statisticsDo.getTableSchema()).
                replace("{tableName}", statisticsDo.getTableName()).
                replace("{columnName}", statisticsDo.getColumnName()).
                replace("{indexName}", statisticsDo.getIndexName()).
                replace(",", "`,`");
    }

    private void executeSql(ConnectInfo connectInfo, List<String> sqls) {
        for (String sql : sqls) {
            DaoFacade.execSql(connectInfo, sql);
        }

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
