package com.example.mysqlschemasync.service.impl;

import com.example.mysqlschemasync.model.*;
import com.example.mysqlschemasync.service.SyncService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

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

    @Override
    public void doSyncTable(SyncTableRequest syncInfo) {
        ConnectInfo srcConnectInfo = syncInfo.getSrcConnectInfo();
        ConnectInfo dstConnectInfo = syncInfo.getDstConnectInfo();
        String dbName = syncInfo.getDbName();
        String tableName = syncInfo.getTableName();
        /**
         * 接下来
         * 1. 去 src 找到对应的 实例.数据库.表 获取字段+索引
         * 2. 去 dst 找到对应的 实例.数据库.表 获取字段+索引
         * 3. diff 差异. 字段不一致,索引不一致
         * 4. 基于差异, 生产 sql
         * 5. 执行 sql
         */
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
