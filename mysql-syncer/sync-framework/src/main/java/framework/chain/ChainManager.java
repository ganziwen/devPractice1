package framework.chain;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import framework.chain.handler.DiffDatabaseHandler;
import framework.chain.handler.DiffInstanceHandler;
import framework.chain.handler.DiffTableHandler;
import org.yaml.snakeyaml.nodes.CollectionNode;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ChainManager
 * @Description
 * @date 2021/10/6 14:41
 */
public final class ChainManager {
    private AbstractHandler<DiffContext> diffHandler;


    private ChainManager() {
        this.diffHandler = initDiffHandlerChain();
    }

    private AbstractHandler<DiffContext> initDiffHandlerChain() {
        AbstractHandler<DiffContext> diffInstanceHandler = new DiffInstanceHandler();
        AbstractHandler<DiffContext> diffDatabaseHandler = new DiffDatabaseHandler();
        AbstractHandler<DiffContext> diffTableHandler = new DiffTableHandler();

        diffInstanceHandler.setNextHandler(diffDatabaseHandler);
        diffDatabaseHandler.setNextHandler(diffTableHandler);
        return null;
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
}

