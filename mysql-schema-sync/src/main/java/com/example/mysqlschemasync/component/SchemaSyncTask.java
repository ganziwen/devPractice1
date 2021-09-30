package com.example.mysqlschemasync.component;

import com.example.mysqlschemasync.service.SyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SchemaSyncTask
 * @Description
 * @date 2021/9/25 9:52
 */
@Component
public class SchemaSyncTask {
    private SyncService syncService;

    @Scheduled(cron = "*/30 * * * * *")
    public void doSync() {
        syncService.healthCheck();
        // syncService.doSyncTable(new SyncTableRequest());
    }
}
