package framework.model;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncDatabaseTask
 * @Description
 * @date 2021/10/10 17:01
 */
public class SyncDatabaseTask extends ITask<String> {

    private SyncDatabaseData syncDatabaseData;

    public SyncDatabaseTask(SyncDatabaseData syncDatabaseData) {
        this.syncDatabaseData = syncDatabaseData;
    }

    @Override
    public String call() throws Exception {
        // 这里是真正的去执行 sql：DaoFacade.exec
        return null;
    }
}
