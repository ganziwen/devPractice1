package framework.chain;

import framework.chain.handler.diff.DiffDatabaseHandler;
import framework.chain.handler.diff.DiffInstanceHandler;
import framework.chain.handler.diff.DiffTableHandler;
import framework.chain.handler.sync.SyncDatabaseHandler;
import framework.chain.handler.sync.SyncInstanceHandler;
import framework.chain.handler.sync.SyncTableHandler;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ChainManager
 * @Description
 * @date 2021/10/6 14:41
 */
public final class ChainManager {
    private AbstractHandler<DiffContext> diffHandler;
    private AbstractHandler<SyncContext> syncHandler;


    private ChainManager() {
        this.diffHandler = initDiffHandlerChain();
        this.syncHandler = initSyncHandlerChain();
    }

    private AbstractHandler<SyncContext> initSyncHandlerChain() {
        SyncInstanceHandler syncInstanceHandler = new SyncInstanceHandler();
        SyncDatabaseHandler syncDatabaseHandler = new SyncDatabaseHandler();
        SyncTableHandler syncTableHandler = new SyncTableHandler();
        syncInstanceHandler.setNextHandler(syncDatabaseHandler);
        syncDatabaseHandler.setNextHandler(syncTableHandler);
        return syncInstanceHandler;
    }

    private AbstractHandler<DiffContext> initDiffHandlerChain() {
        AbstractHandler<DiffContext> diffInstanceHandler = new DiffInstanceHandler();
        AbstractHandler<DiffContext> diffDatabaseHandler = new DiffDatabaseHandler();
        AbstractHandler<DiffContext> diffTableHandler = new DiffTableHandler();

        diffInstanceHandler.setNextHandler(diffDatabaseHandler);
        diffDatabaseHandler.setNextHandler(diffTableHandler);
        return diffInstanceHandler;
    }

    private static class ClassHolder {
        private static final ChainManager HOLDER = new ChainManager();
    }

    public static ChainManager of() {
        return ClassHolder.HOLDER;
    }

    public void diff(DiffContext diffContext) {
        this.diffHandler.doHandle(diffContext);
    }

    public void sync(SyncContext syncContext) {
        this.syncHandler.doHandle(syncContext);
    }
}

