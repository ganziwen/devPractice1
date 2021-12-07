// package module;
//
// import com.alibaba.fastjson.JSON;
// import com.alibaba.jvm.sandbox.api.Information;
// import com.alibaba.jvm.sandbox.api.Module;
// import com.alibaba.jvm.sandbox.api.annotation.Command;
// import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
// import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
// import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
// import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
// import org.kohsuke.MetaInfServices;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// import javax.annotation.Resource;
//
// /**
//  * @author steven01.gan
//  * @version 1.0
//  */
//
// @MetaInfServices(Module.class)
// @Information(id = "pallas-rest-analyse", version = "0.0.1", author = "steven01.gan")
// public class PallasRestAnalyseModule implements Module {
//     @Resource
//     private ModuleEventWatcher moduleEventWatcher;
//
//     private final Logger logger = LoggerFactory.getLogger("PallasRestAnalyseModule");
//     private final String logSep = "*********************************************";
//
//     @Command("perform")
//     public void perform4Puma() {
//         new EventWatchBuilder(moduleEventWatcher).onClass("*EsResolveService").includeSubClasses().onBehavior("performTemplateRequest")
//                 .onWatch(new AdviceListener() {
//                     @Override
//                     public void before(Advice advice) {
//                         Object[] objects = advice.getParameterArray();
//                         if (objects.length > 3) {
//                             logger.info(logSep);
//                             logger.info("id:" + JSON.toJSONString(objects[2]));
//                             logger.info("params:" + JSON.toJSONString(objects[3]));
//                         }
//                     }
//
//                     @Override
//                     public void afterReturning(Advice advice) {
//                         try {
//                             if (advice.getReturnObj().getClass().getName().contains("EsSearchResult")) {
//                                 logger.info("result:" + JSON.toJSONString(advice.getReturnObj()));
//                                 logger.info(logSep + "\n");
//                             }
//                         } catch (Exception e) {
//                             logger.error(JSON.toJSONString(e));
//                         }
//                     }
//                 });
//     }
//
//     @Command("perform4Puma")
//     public void perform4Query() {
//         new EventWatchBuilder(moduleEventWatcher).onClass("*EsResolveService").includeSubClasses().onBehavior("performTemplateRequest")
//                 .onWatch(new AdviceListener() {
//                     @Override
//                     public void before(Advice advice) {
//                         Object[] objects = advice.getParameterArray();
//                         if (objects.length > 3) {
//                             logger.info(logSep);
//                             logger.info("id:" + JSON.toJSONString(objects[2]));
//                             logger.info("params:" + JSON.toJSONString(objects[3]));
//                         }
//                     }
//
//                     @Override
//                     public void afterReturning(Advice advice) {
//                         try {
//                             if (advice.getReturnObj().getClass().getName().contains("EsSearchResult")) {
//                                 logger.info("result:" + JSON.toJSONString(advice.getReturnObj()));
//                                 logger.info(logSep + "\n");
//                             }
//                         } catch (Exception e) {
//                             logger.error(JSON.toJSONString(e));
//                         }
//                     }
//                 });
//     }
//
//
// }
//
