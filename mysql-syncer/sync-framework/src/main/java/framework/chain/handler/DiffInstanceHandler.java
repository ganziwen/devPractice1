package framework.chain.handler;

import framework.chain.AbstractHandler;
import framework.chain.DiffContext;
import framework.chain.DiffResponse;
import framework.enums.DiffType;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffInstanceHandler
 * @Description
 * @date 2021/10/6 14:06
 */
public class DiffInstanceHandler extends AbstractHandler<DiffResponse, DiffContext> {
    @Override
    protected boolean preHandle(DiffContext diffContext) {
        return diffContext.getDiffType().equals(DiffType.INSTANCE);
    }

    @Override
    protected DiffResponse onHandle(DiffContext diffContext) {
        diffContext.getSrcConnectInfo();
        diffContext.getDstConnectInfo();
        return null;
    }
}

