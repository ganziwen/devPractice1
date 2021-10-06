package framework.chain.handler;

import framework.chain.AbstractHandler;
import framework.chain.DiffContext;
import framework.chain.DiffResponse;
import framework.enums.DiffType;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffDatabase
 * @Description
 * @date 2021/10/6 14:34
 */
public class DiffDatabaseHandler extends AbstractHandler<DiffResponse, DiffContext> {

    @Override
    protected boolean preHandle(DiffContext diffContext) {
        return diffContext.getDiffType().equals(DiffType.DATABESE);
    }

    @Override
    protected DiffResponse onHandle(DiffContext diffContext) {
        return null;
    }
}
