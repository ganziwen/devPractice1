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
@Information(id = "sql-data", version = "0.0.1", author = "steven01.gan")
public class SqlDataModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    private final Logger logger = LoggerFactory.getLogger("DataModule");

    @Command("getSql")
    public void getSql() {

        new EventWatchBuilder(moduleEventWatcher).onClass("org.apache.ibatis.mapping.MappedStatement")
                .onBehavior("getBoundSql")
                .onWatch(new AdviceListener() {

                    @Override
                    public void after(Advice advice) throws Throwable {

                        if (advice.getInvokeId() == advice.getProcessId()) {//解决sql打印2次的问题

                            String type = "NONE";
                            StringBuilder message = new StringBuilder();

                            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();

                            for (int i = 0; i < stacks.length; i++) {

                                //过滤掉非业务调用链
                                if (!stacks[i].getClassName().startsWith("com") || stacks[i].getClassName().contains("sandbox")) {
                                    continue;
                                }
                                try {
                                    Method method = null;
                                    Method[] methods = Class.forName(stacks[i].getClassName()).getDeclaredMethods();
                                    for (Method m : methods) {
                                        if (m.getName().equals(stacks[i].getMethodName())) {
                                            method = m;
                                            break;
                                        }
                                    }
                                    if (method == null) {
                                        continue;
                                    }

                                    method.setAccessible(true);

                                    // 这里控制读写属性的拦截
                                    String readWrite = "ReadWrite";

                                    Annotation[] annotations = method.getAnnotations();
                                    for (Annotation annotation : annotations) {
                                        if (annotation.annotationType().getSimpleName().equals(readWrite)) {
                                            String value = JSON.toJSONString(annotation);
                                            String msg = String.format("[%s.%s]有注解：%s", method.getDeclaringClass().getSimpleName(), method.getName(), value);
                                            // message += msg;
                                            message.append(msg);
                                            logger.info(msg);
                                            if (value.contains("READ")) {
                                                type = "READ";
                                            } else if (value.contains("WRITE")) {
                                                type = "WRITE";
                                            }
                                        }
                                    }
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

                                if ("READ".equals(type) && !"SELECT".equals(sqlCommandType.toString())) {
                                    String readOnlyMsg = String.format("[读写分离][%s]限制只读，但进行了写操作:%s", message.toString(), sql);
                                    logger.error(readOnlyMsg);
                                    ProcessController.throwsImmediately(new Exception(readOnlyMsg));
                                }

                            } catch (NoSuchFieldException e) {
                                logger.error("找不到属性", e);
                            } catch (IllegalAccessException e) {
                                logger.error("未知异常", e);
                            }

                        }
                    }

                });
    }

}
