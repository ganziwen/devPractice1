package framework.chain.handler.sync;

import framework.chain.AbstractHandler;
import framework.chain.SyncContext;
import framework.observe.Context;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncInstanceHandler
 * @Description
 * @date 2021/10/10 16:09
 */
public class SyncInstanceHandler extends AbstractHandler<SyncContext> {
    @Override
    protected boolean preHandle(SyncContext syncContext) {
        return false;
    }

    @Override
    protected void onHandle(SyncContext syncContext) {

    }
}

