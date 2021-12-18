package com.example.autoframework.driver;

import cn.hutool.log.StaticLog;
import com.example.autoframework.annotation.DataDriver;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DataDriverExtension
 * @Description
 * @date 2021/12/12 16:50
 */
public class DataDriverExtension implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        // 不管三七二十一全部返回模板
        // return true;

        // 要判断被执行的方法之上被标注了 @DataDriver 注解,有这个注解才走这个模板
        // 相当于我们之前写 Handler 时的 preHandle 的前置判断，也就是说将 if 这种判断与业务代码的剥离，与我们玩的一样
        return extensionContext.getTestMethod().filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class)).isPresent();
    }

    /**
     * 这里要生成一个 TestTemplateInvocationContext 的 stream，TestTemplateInvocationContext 是个接口，TestTemplateInvocationContext 其实就是参数化拿到的每一行数据
     *
     * @param extensionContext
     * @return
     */
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        // 在这里要去找到 DataDriver 所指定的 path 下的文件,并解析出来
        // 解析出来的数据是 list<T>
        // 那么我们要做的就是将 T -> new DataDriverInvocationContext()
        // 最后将所有 list 中的 T 都转成了 DataDriverInvocationContext 之后，整体装进 Stream 返回

        // 拿到了 testMethod
        Method requiredTestMethod = extensionContext.getRequiredTestMethod();
        // 这里是判断拿到的 testMethod 是有注解的，其实上面的 supportsTestTemplate 也判断了，假设上面粗暴返回了那么这里就得去判断了
        boolean annotated = AnnotationSupport.isAnnotated(requiredTestMethod, DataDriver.class);
        if (annotated) {
            // 拿到注解的信息
            DataDriver dataDriver = requiredTestMethod.getAnnotation(DataDriver.class);

            StaticLog.info("dataDriver.path()=" + dataDriver.path());
        }
        return Stream.of(new DataDriverInvocationContext());
    }

    /**
     * 用个匿名内部类实现 TestTemplateInvocationContext 接口和 ParameterResolver
     * ParameterResolver 值的是处理参数的接口，不实现的话会报：No ParameterResolver registered for parameter
     */
    static class DataDriverInvocationContext implements TestTemplateInvocationContext, ParameterResolver {
        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            // 同样也是要加注解才能正常返回
            return extensionContext.getTestMethod().filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class)).isPresent();

            // 不管三七二十一全部返回模板
            // return true;
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            // 在这里完成每一个数据到参数的转化，比如从资源文件中拿到的数据是 key = value ，value = zhangsan
            // 那么我们要做的事就是找参数名是 name 的，然后返回 zhangsan
            return "testInfo";
        }
    }

}
