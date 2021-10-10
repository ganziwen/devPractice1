package framework.observe;

import framework.chain.ChainManager;
import framework.chain.SyncContext;
import framework.enums.CommandType;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffObserver
 * @Description
 * @date 2021/10/10 11:36
 */

public class SyncObserver implements IObserve<Context> {

    @Override
    public boolean preUpdate(Context context) {
        return CommandType.SYNC.equals(context.getCommandType()) || CommandType.DIFF_AND_SYNC.equals(context.getCommandType());
    }

    @Override
    public void update(Context context) {
        SyncContext syncContext = new SyncContext();
        // TODO: 2021/10/10 参数构造
        ChainManager.of().sync(syncContext);
    }
}
