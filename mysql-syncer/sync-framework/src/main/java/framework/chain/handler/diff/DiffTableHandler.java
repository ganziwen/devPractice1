package framework.chain.handler.diff;

import framework.chain.AbstractHandler;
import framework.chain.DiffContext;
import framework.chain.DiffResponse;
import framework.enums.DiffType;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffTableHandler
 * @Description
 * @date 2021/10/6 14:35
 */
public class DiffTableHandler extends AbstractHandler<DiffContext> {

    @Override
    protected boolean preHandle(DiffContext diffContext) {
        return diffContext.getDiffType().equals(DiffType.TABLE);
    }

    @Override
    protected void onHandle(DiffContext diffContext) {

    }
}
