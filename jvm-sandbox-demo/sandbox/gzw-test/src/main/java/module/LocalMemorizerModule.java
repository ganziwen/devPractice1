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
// import params.ParamSupported;
//
// import javax.annotation.Resource;
// import java.util.Map;
//
// /**
//  * @author steven01.gan
//  * @version 1.0
//  */
//
// @MetaInfServices(Module.class)
// @Information(id = "local-data", version = "0.0.1", author = "steven01.gan")
// public class LocalMemorizerModule extends ParamSupported implements Module {
//
//     @Resource
//     private ModuleEventWatcher moduleEventWatcher;
//
//     private final Logger logger = LoggerFactory.getLogger("DataModule");
//
//
//     /*
//      * -d 'local-data/local?key=<KEY>'
//      */
//     @Command("local")
//     public void local(final Map<String, String> param) {
//         String packageKey =  getParameter(param, "key");
//         String pattern = String.format("*%s*Memorizer",packageKey);//*memorydata*Memorizer
//         new EventWatchBuilder(moduleEventWatcher).onClass(pattern).onBehavior("*")
//                 .onWatch(new AdviceListener() {
//                     @Override
//                     public void before(Advice advice) {
//
//                     }
//
//                     @Override
//                     public void afterReturning(Advice advice) {
//
//                         String className = advice.getBehavior().getDeclaringClass().getSimpleName();
//                         String methodName = advice.getBehavior().getName();
//
//                         //public方法才打印结果
//                         if (StringUtils.isNotEmpty(methodName) && !methodName.contains("jacoco") && advice.getBehavior().getTarget().toString().startsWith("public")) {
//                             try {
//                                 logger.info("*************************before******************************\n");
//                                 String key = JSON.toJSONString(advice.getParameterArray());
//                                 Object returnObj = advice.getReturnObj();
//                                 String value = JSON.toJSONString(returnObj);
//
//                                 logger.info(String.format("调用方法[%s.%s],\n入参:[%s],\n返回值:[%s]\n", className, methodName, key, value));
//
//
//                             } catch (Exception e) {
//                                 logger.info(JSON.toJSONString(e));
//                             }
//                             logger.info("*************************after******************************\n\n\n");
//                         }
//                     }
//                 });
//
//     }
//
// }
//
