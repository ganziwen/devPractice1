package module;

import com.alibaba.fastjson.JSON;
import com.alibaba.jvm.sandbox.api.Information;
import com.alibaba.jvm.sandbox.api.Module;
import com.alibaba.jvm.sandbox.api.annotation.Command;
import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
import org.apache.commons.lang3.StringUtils;
import org.kohsuke.MetaInfServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author steven01.gan
 * @version 1.0
 */

@MetaInfServices(Module.class)
@Information(id = "redis-data", version = "0.0.1", author = "steven01.gan")
public class RedisDataModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    private final Logger logger = LoggerFactory.getLogger("DataModule");


    @Command("get")
    public void commonGet() {
        new EventWatchBuilder(moduleEventWatcher).onClass("*AbstractSingleRedisMemorizer").onBehavior("get")
                .onWatch(new AdviceListener() {

                    @Override
                    public void before(Advice advice) {

                    }

                    @Override
                    public void afterReturning(Advice advice) {
                        try {
                            String key = JSON.toJSONString(advice.getParameterArray());

                            Object returnObj = advice.getReturnObj();
                            String value = JSON.toJSONString(returnObj);

                            logger.info(String.format("key:[%s],value:[%s]", key, value));

                        } catch (Exception e) {
                            logger.info(JSON.toJSONString(e));
                        }
                    }

                });
    }


    @Command("monitorRedisUtils")
    public void redisUtil() {
        new EventWatchBuilder(moduleEventWatcher).onClass("*RedisUtils").onBehavior("*")
                .onWatch(new AdviceListener() {
                    @Override
                    public void before(Advice advice) {

                    }

                    @Override
                    public void afterReturning(Advice advice) {
                        String methodName = advice.getBehavior().getName();

                        //public方法才打印结果
                        if (StringUtils.isNotEmpty(methodName) && !methodName.contains("jacoco") && advice.getBehavior().getTarget().toString().startsWith("public")) {
                            try {
                                logger.info("*************************before******************************\n");
                                String key = JSON.toJSONString(advice.getParameterArray());
                                Object returnObj = advice.getReturnObj();
                                String value = JSON.toJSONString(returnObj);

                                logger.info(String.format("调用方法[RedisUtils.%s],\n入参:[%s],\n返回值:[%s]\n", methodName, key, value));


                            } catch (Exception e) {
                                logger.info(JSON.toJSONString(e));
                            }
                            logger.info("*************************after******************************\n\n\n");
                        }
                    }
                });

    }

}

