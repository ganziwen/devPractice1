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
// import org.apache.commons.lang3.StringUtils;
// import org.kohsuke.MetaInfServices;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import utils.InvokedUtils;
//
// import javax.annotation.Resource;
//
// /**
//  * @author steven01.gan
//  * @version 1.0
//  */
//
// @MetaInfServices(Module.class)
// @Information(id = "error-log", version = "0.0.1", author = "steven01.gan")
// public class ErrorLogModule implements Module {
//     @Resource
//     private ModuleEventWatcher moduleEventWatcher;
//
//     private final Logger logger = LoggerFactory.getLogger("ErrorLogModule");
//
//     @Command("getLog")
//     public void getLog() {
//         new EventWatchBuilder(moduleEventWatcher).onClass("*Logger*").onBehavior("*")
//                 .onWatch(new AdviceListener() {
//
//                     @Override
//                     public void before(Advice advice) {
//
//                     }
//
//                     @Override
//                     public void afterReturning(Advice advice) {
//                         try {
//
//                             Object object = advice.getTarget();
//                             if (object.getClass().getSimpleName().equals("Logger")) {
//                                 String log = object.toString() + JSON.toJSONString(advice.getParameterArray());
//                                 if (StringUtils.isNotEmpty(log)) {
//                                     logger.info(log);
//                                 }
//                             }
//
//                         } catch (Exception e) {
//                             logger.info(JSON.toJSONString(e));
//                         }
//                     }
//                 });
//     }
//
//     @Command("getOspLog")
//     public void getOspLog() {
//         new EventWatchBuilder(moduleEventWatcher).onClass("*VmockMessageParser*").onBehavior("parseMessage")
//                 .onWatch(new AdviceListener() {
//
//                     @Override
//                     public void before(Advice advice) {
//
//                     }
//
//                     @Override
//                     public void afterReturning(Advice advice) {
//                         try {
//
//                             Object object = advice.getReturnObj();
//
//                             if (object.getClass().getSimpleName().equals("OspMessage")) {
//                                 String method = InvokedUtils.getPrivateField(object, "method").toString();
//
//                                 if (!"healthCheck".equals(method)) {
//                                     String serviceName = InvokedUtils.getPrivateField(object, "serviceName").toString();
//                                     Object plainPayload = InvokedUtils.getPrivateField(object, "plainPayload");
//                                     logger.info(String.format("%s.%s:\n%s", serviceName, method, JSON.toJSONString(plainPayload)));
//                                 }
//                             }
//
//                         } catch (Exception e) {
//                             logger.info(JSON.toJSONString(e));
//                         }
//                     }
//                 });
//     }
//
// }
