package com.example.mysqlschemasync.service.impl;

import com.example.mysqlschemasync.model.SyncDatabaseRequest;
import com.example.mysqlschemasync.model.SyncInfo;
import com.example.mysqlschemasync.model.SyncInstaceRequest;
import com.example.mysqlschemasync.model.SyncTableRequest;
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

    }

    @Override
    public void doSync(SyncInfo syncInfo) {

        syncInfo.getSrcConnectInfo();
        syncInfo.getDstConnectInfo();
        /**
         * 如果Controller层不校验是同步实例级别还是库级别还是表级别, 就需要在这一层判断.
         * 那么这里考虑多态么?
         * 有表名代表是表级别,
         * 有库名代表库级别,
         * 两个都没代表实例级别
         */

    }
}
