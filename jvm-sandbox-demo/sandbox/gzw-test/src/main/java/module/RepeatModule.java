// package module;
//
// import com.alibaba.fastjson.JSON;
// import com.alibaba.jvm.sandbox.api.annotation.Command;
// import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
// import com.alibaba.jvm.sandbox.api.listener.ext.Behavior;
// import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
// import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
// import org.apache.commons.lang3.StringUtils;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import params.ParamSupported;
// import utils.ExecutorUtils;
// import utils.InvokedUtils;
// import com.alibaba.jvm.sandbox.api.Module;
// import com.alibaba.jvm.sandbox.api.Information;
// import com.alibaba.jvm.sandbox.api.Module;
// import com.alibaba.jvm.sandbox.api.ProcessController;
// import com.alibaba.jvm.sandbox.api.annotation.Command;
// import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
// import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
// import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
// import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
//
// import javax.annotation.Resource;
// import java.lang.reflect.InvocationTargetException;
// import java.lang.reflect.Method;
// import java.lang.reflect.Type;
// import java.util.Arrays;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.concurrent.Callable;
// import java.util.concurrent.FutureTask;
//
// /**
//  * @author steven01.gan
//  * @version 1.0
//  * @date 2022/2/16-12:30
//  */
// public class RepeatModule extends ParamSupported implements Module {
//     @Resource
//     private ModuleEventWatcher moduleEventWatcher;
//     private final Logger repeatLogger = LoggerFactory.getLogger("RepeatDataModule");
//     private final Logger logger = LoggerFactory.getLogger("DataModule");
//
//     public RepeatModule() {
//     }
//
//     @Command("call")
//     public void call(Map<String, String> param) {
//         String timeStr = getParameter(param, "times");
//         String methods = getParameter(param, "methods");
//         Integer times = 0;
//         if (StringUtils.isNotEmpty(timeStr)) {
//             try {
//                 times = Integer.valueOf(timeStr);
//             } catch (Exception var7) {
//             }
//         }
//
//         final List<String> methodNameList = StringUtils.isNotEmpty(methods) ? Arrays.asList(methods.split(",")) : null;
//         new EventWatchBuilder(this.moduleEventWatcher).onClass("com.vip.osp.core.base.MethodDispatcher").onBehavior("invokeMethod").onWatch(new AdviceListener() {
//
//             @Override
//             public void before(final Advice advice) {
//                 if (times > 0) {
//                     final Behavior behavior = advice.getBehavior();
//                     String adviceClassName = advice.getTarget().getClass().getName();
//                     String ospMethodName = adviceClassName.split("\\$")[1].split("_Dispatcher")[0];
//                     if (!"healthCheck".equals(ospMethodName) && !"getServiceDescriptor".equals(ospMethodName) && (methodNameList == null || methodNameList.indexOf(ospMethodName) != -1)) {
//                         StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
//                         StackTraceElement[] var6 = stacks;
//                         int var7 = stacks.length;
//
//                         StackTraceElement stack;
//                         for (int var8 = 0; var8 < var7; ++var8) {
//                             stack = var6[var8];
//                             if (stack.getClassName().equals(behavior.getClass().getName()) && stack.getFileName().equals("Behavior.java") && stack.getMethodName().equals("invoke")) {
//                                 return;
//                             }
//                         }
//
//                         String ospServiceName = adviceClassName.split("ProviderHelper")[0];
//                         final Object[] objectlist = advice.getParameterArray();
//                         Object cookie = null;
//                         stack = null;
//
//                         try {
//                             HashMap<String, Object> filterContext = (HashMap) InvokedUtils.getPrivateField(advice.getParameterArray()[2], "filterContext");
//                             Object mercuryCtx = filterContext.get("mercuryCtx");
//                             Object transactionContext = InvokedUtils.getPrivateField(mercuryCtx, "transactionContext");
//                             cookie = InvokedUtils.getPrivateField(transactionContext, "cookie");
//                         } catch (NoSuchFieldException var16) {
//                             RepeatModule.this.repeatLogger.error("反射获取属性失败", var16);
//                         } catch (IllegalAccessException var17) {
//                             RepeatModule.this.repeatLogger.error("反射操作属性失败", var17);
//                         }
//
//                         RepeatModule.this.printRepeatLog("INFO", String.format("\n重复调用[%s]次\n%s.%s\n%s\n", times, ospServiceName, ospMethodName, JSON.toJSONString(objectlist[1])));
//                         if (cookie != null) {
//                             RepeatModule.this.printRepeatLog("INFO", String.format("cookie:%s\n", JSON.toJSONString(cookie)));
//                         }
//
//                         behavior.setAccessible(true);
//                         Class factory = null;
//
//                         try {
//                             factory = Class.forName("com.vip.osp.core.context.TransactionContextImpl");
//                         } catch (ClassNotFoundException var15) {
//                             RepeatModule.this.repeatLogger.error("反射获取com.vip.osp.core.context.TransactionContextImpl类失败", var15);
//                         }
//
//                         for (int i = 0; i < times; ++i) {
//                             Callable<Object> callable = new Callable<Object>() {
//
//                                 @Override
//                                 public Object call() {
//                                     if (cookie != null && factory != null) {
//                                         try {
//                                             Method newInstance = factory.getDeclaredMethod("newInstance");
//                                             Object invocationContext = newInstance.invoke(factory);
//                                             Method setCookie = invocationContext.getClass().getDeclaredMethod("setCookie", Map.class);
//                                             setCookie.invoke(invocationContext, cookie);
//                                         } catch (Exception var4) {
//                                             var4.printStackTrace();
//                                         }
//                                     }
//
//                                     return RepeatModule.this.invoke(behavior, advice.getTarget(), objectlist);
//                                 }
//                             };
//                             ExecutorUtils.submit(new FutureTask(callable));
//                         }
//
//                     }
//                 }
//             }
//         }, new Type[0]);
//     }
//
//     private Object invoke(Behavior behavior, Object target, Object[] objectlist) {
//         Object result = null;
//
//         try {
//             result = behavior.invoke(target, objectlist);
//         } catch (IllegalAccessException var6) {
//             var6.printStackTrace();
//         } catch (InvocationTargetException var7) {
//             var7.printStackTrace();
//         } catch (InstantiationException var8) {
//             var8.printStackTrace();
//         }
//
//         return result;
//     }
//
//     private void printRepeatLog(String level, String msg) {
//         byte var4 = -1;
//         switch (level.hashCode()) {
//             case 66247144:
//                 if (level.equals("ERROR")) {
//                     var4 = 0;
//                 }
//             default:
//                 switch (var4) {
//                     case 0:
//                         this.logger.error(msg);
//                         this.repeatLogger.error(msg);
//                         break;
//                     default:
//                         this.logger.info(msg);
//                         this.repeatLogger.info(msg);
//                 }
//
//         }
//     }
// }
//
