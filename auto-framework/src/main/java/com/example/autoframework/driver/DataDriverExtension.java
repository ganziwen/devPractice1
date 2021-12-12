package com.example.autoframework.driver;

import com.example.autoframework.annotation.DataDriver;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

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
            return null;
        }
    }

}
