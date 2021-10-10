package framework;

import framework.observe.Context;
import framework.observe.ObserverManager;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncFacade
 * @Description
 * @date 2021/10/10 11:46
 */
public final class SyncFacade {
    public SyncFacade() {
    }

    public void doSyncer(Context context) {
        ObserverManager.of().update(context);
    }
}
