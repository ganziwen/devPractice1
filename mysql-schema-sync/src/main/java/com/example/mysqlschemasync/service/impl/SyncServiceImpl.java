package com.example.mysqlschemasync.service.impl;

import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.ColumnsMapper;
import com.example.mysqlschemasync.mapper.StatisticsMapper;
import com.example.mysqlschemasync.mapper.TablesMapper;
import com.example.mysqlschemasync.model.*;
import com.example.mysqlschemasync.service.SyncService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Sync
 * @Description
 * @date 2021/8/29 11:40
 */
@Service
public class SyncServiceImpl implements SyncService {
    @Override
    public void doSyncInstance(SyncInstaceRequest syncInfo) {
    }

    @Override
    public void doSyncDatabse(SyncDatabaseRequest syncInfo) {

    }

    /**
     * 整体的思路
     *
     * @param syncInfo
     */
    @Override
    public void doSyncTable(SyncTableRequest syncInfo) {
        // 信息获取,后面会删除的
        ConnectInfo srcConnectInfo = syncInfo.getSrcConnectInfo();
        ConnectInfo dstConnectInfo = syncInfo.getDstConnectInfo();
        String dbName = syncInfo.getDbName();
        String tableName = syncInfo.getTableName();

        // 1. 去 src 找到对应的 实例.数据库.表 获取字段+索引
        List<ColumnsDo> srcColumns = DaoFacade.ofMapper(srcConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        List<StatisticsDo> srcStatistics = DaoFacade.ofMapper(srcConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));

        // 2. 去 dst 找到对应的 实例.数据库.表 获取字段+索引
        List<ColumnsDo> dstColumns = DaoFacade.ofMapper(dstConnectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable(dbName, tableName));
        List<StatisticsDo> dstStatistics = DaoFacade.ofMapper(dstConnectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable(dbName, tableName));

        // 3. diff 差异. 字段不一致,索引不一致
        List<DifferenceInfo> diffColumn = diffColumn(srcColumns, dstColumns);
        List<DifferenceInfo> diffStatistics = diffStatistics(srcStatistics, dstStatistics);

        //  4. 基于差异, 生产 sql
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
    private List<DifferenceInfo> diffColumn(List<ColumnsDo> srcColumns, List<ColumnsDo> dstColumns) {
        // 对比 src 和 dst,不管是长度还是注释还是默认值不一致,只要存在这些情况就以 src 为准,生成 sql 执行语句
        return null;
    }

    private List<DifferenceInfo> diffStatistics(List<StatisticsDo> srcStatistics, List<StatisticsDo> dstStatistics) {
        return null;
    }

    private List<String> getColumnSql(List<DifferenceInfo> diffColumn) {
        return null;
    }

    private List<String> getStatisticsSql(List<DifferenceInfo> diffStatistics) {
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
