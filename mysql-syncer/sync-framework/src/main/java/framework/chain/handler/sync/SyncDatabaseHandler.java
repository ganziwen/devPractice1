package framework.chain.handler.sync;

import framework.chain.AbstractHandler;
import framework.chain.SyncContext;
import framework.model.SyncDatabaseData;
import framework.model.SyncDatabaseTask;
import framework.thread.SyncThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncInstanceHandler
 * @Description
 * @date 2021/10/10 16:09
 */
public class SyncDatabaseHandler extends AbstractHandler<SyncContext> implements Callable<String> {
    @Override
    protected boolean preHandle(SyncContext syncContext) {
        return false;
    }

    @Override
    protected void onHandle(SyncContext syncContext) {
        // 将 diff 阶段拿到的 sql 去 dst 执行
        SyncDatabaseData syncDatabaseData = SyncDatabaseData.builder()
                .connectInfo(syncContext.getDstConnectInfo())
                .database(syncContext.getDatabase())
                .sqlList(null)
                .build();

        Future<String> result = SyncThreadPool.of().run(this);
    }

    @Override
    public String call() throws Exception {
        return null;
    }
}


