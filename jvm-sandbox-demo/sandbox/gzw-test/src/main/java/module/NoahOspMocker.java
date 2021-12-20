package module;

import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.kohsuke.MetaInfServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-18:13
 */

@MetaInfServices(Module.class)
@Information(id = "mock-osp", version = "0.0.1", author = "steven01.gan")
public class NoahOspMocker implements Module {
    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    private final Logger logger = LoggerFactory.getLogger("DataModule");

    @Command("MockOsp")
    public void mockOsp() {
        String className = "com.vip.fcs.vpos.service.inf.gpdc.GpdcOspService";
        String methodName = "querySkuInfo";

        new EventWatchBuilder(moduleEventWatcher).onClass(className)
                .onBehavior(methodName)
                .onWatch(new AdviceListener() {
                    // 这里写拦截的逻辑
                });

    }
}
