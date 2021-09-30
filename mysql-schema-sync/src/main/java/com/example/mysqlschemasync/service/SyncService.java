package com.example.mysqlschemasync.service;

import com.example.mysqlschemasync.model.SyncDatabaseRequest;
import com.example.mysqlschemasync.model.SyncInfo;
import com.example.mysqlschemasync.model.SyncInstanceRequest;
import com.example.mysqlschemasync.model.SyncTableRequest;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncService
 * @Description
 * @date 2021/8/29 11:38
 */
public interface SyncService<T> {
    /**
     * 同步:可用多态实现
     *
     * @param syncInfo
     */
    void doSync(SyncInfo syncInfo);

    /**
     * 同步实例级别
     *
     * @param syncInfo
     */
    void doSyncInstance(SyncInstanceRequest syncInfo);

    /**
     * 同步库级别
     *
     * @param syncInfo
     */
    void doSyncDatabase(SyncDatabaseRequest syncInfo);

    /**
     * 同步表级别
     *
     * @param syncInfo
     */
    void doSyncTable(SyncTableRequest syncInfo);

    /**
     * 健康检查
     */
    void healthCheck();
}
