package framework.observe;

import framework.chain.ChainManager;
import framework.chain.DiffContext;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffObserver
 * @Description
 * @date 2021/10/10 11:36
 */

public class DiffObserver implements IObserve<Context> {

    @Override
    public void update(Context context) {
        DiffContext diffContext = DiffContext.builder()
                .srcConnectInfo(context.getSrcConnectInfo())
                .dstConnectInfo(context.getDstConnectInfo())
                .build();
        ChainManager.of().diff(diffContext);
    }
}
