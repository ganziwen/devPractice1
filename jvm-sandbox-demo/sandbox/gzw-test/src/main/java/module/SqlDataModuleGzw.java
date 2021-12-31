package module;

import com.alibaba.fastjson.JSON;
import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.ProcessController;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.apache.commons.lang3.StringUtils;
import org.kohsuke.MetaInfServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.InvokedUtils;
import utils.SqlUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author steven01.gan
 * @version 1.0
 * 监控的日志在 logback.xml 内进行配置的
 */

@MetaInfServices(Module.class)
@Information(id = "sql-data-simple", version = "0.0.1", author = "steven01.gan")
public class SqlDataModuleGzw implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    private final Logger logger = LoggerFactory.getLogger("DataModule");

    @Command("getSqlSimple")
    public void getSql() {

        new EventWatchBuilder(moduleEventWatcher).onClass("org.apache.ibatis.executor.SimpleExecutor")
                .onBehavior("prepareStatement")
                .onWatch(new AdviceListener() {

                    @Override
                    public void after(Advice advice) throws Throwable {
                        String type = "NONE";
                        StringBuilder message = new StringBuilder();
                        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
                        for (StackTraceElement stack : stacks) {
                            //过滤掉非业务调用链
                            if (!stack.getClassName().startsWith("com") || stack.getClassName().contains("sandbox")) {
                                continue;
                            }

                            try {
                                Method method = null;
                                Method[] methods = Class.forName(stack.getClassName()).getDeclaredMethods();
                                for (Method m : methods) {
                                    if (m.getName().equals(stack.getMethodName())) {
                                        method = m;
                                        break;
                                    }
                                }
                                if (method == null) {
                                    continue;
                                }
                                method.setAccessible(true);

                            } catch (Exception e) {
                                logger.debug("获取注解发生异常", e);
                            }
                        }
                        Object object = advice.getTarget();

                        String sql = "";
                        String className = object.getClass().getSimpleName();

                        try {
                            Object sqlCommandType = InvokedUtils.getPrivateField(object, "sqlCommandType");
                            Object configuration = InvokedUtils.getPrivateField(object, "configuration");
                            Object boundSql = advice.getReturnObj();

                            sql = SqlUtils.showSql(configuration, boundSql);


                            if (StringUtils.isNotEmpty(sql) && !sql.contains("sequence") && !sql.contains("heart_beat")) {
                                logger.info(String.format("[%s][%s][%s]%s", className, type, sqlCommandType, sql));
                            }


                        } catch (NoSuchFieldException e) {
                            logger.error("找不到属性", e);
                        } catch (IllegalAccessException e) {
                            logger.error("未知异常", e);
                        }

                    }

                });
    }

}
