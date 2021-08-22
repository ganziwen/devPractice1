package com.example.mysqlschemasync.service;

import com.example.mysqlschemasync.model.SyncDatabaseRequest;
import com.example.mysqlschemasync.model.SyncInfo;
import com.example.mysqlschemasync.model.SyncInstaceRequest;
import com.example.mysqlschemasync.model.SyncTableRequest;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncService
 * @Description
 * @date 2021/8/29 11:38
 */
public interface SyncService<T> {
    void doSync(SyncInfo syncInfo);
    void doSyncInstance(SyncInstaceRequest syncInfo);
    void doSyncDatabse(SyncDatabaseRequest syncInfo);
    void doSyncTable(SyncTableRequest syncInfo);
}
