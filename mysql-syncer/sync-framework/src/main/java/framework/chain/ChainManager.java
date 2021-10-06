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
    private AbstractHandler<DiffResponse, DiffContext> diffHandler;


    private ChainManager() {
        this.diffHandler = initDiffHandlerChain();
    }

    private AbstractHandler<DiffResponse, DiffContext> initDiffHandlerChain() {
        AbstractHandler<DiffResponse, DiffContext> diffInstanceHandler = new DiffInstanceHandler();
        AbstractHandler<DiffResponse, DiffContext> diffDatabaseHandler = new DiffDatabaseHandler();
        AbstractHandler<DiffResponse, DiffContext> diffTableHandler = new DiffTableHandler();

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

    public DiffResponse diff(DiffContext diffContext) {
        return this.diffHandler.doHandle(diffContext);
    }
}

