package com.example.autoframework.driver;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import com.example.autoframework.annotation.DataDriver;
import com.example.autoframework.annotation.DataParam;
import com.example.autoframework.model.DataEntity;
import com.example.autoframework.util.YmlUtils;
import com.sun.org.apache.bcel.internal.generic.INEG;
import com.sun.org.apache.bcel.internal.generic.RET;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DataDriverExtension
 * @Description TestTemplateInvocationContextProvider 是测试模板的上下文提供者
 * @date 2021/12/12 16:50
 */
public class DataDriverExtension implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext extensionContext) {
        // 不管三七二十一全部返回模板
        // return true;

        // 要判断被执行的方法之上被标注了 @DataDriver 注解,有这个注解才走这个模板
        // 相当于我们之前写 Handler 时的 preHandle 的前置判断，也就是说将 if 这种判断与业务代码的剥离，与我们玩的一样
        return extensionContext
                .getTestMethod()
                .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class))
                .isPresent();
    }

    /**
     * 这里要生成一个 TestTemplateInvocationContext 的 stream，TestTemplateInvocationContext 是个接口，TestTemplateInvocationContext 其实就是参数化拿到的每一行数据
     *
     * @param extensionContext
     * @return
     */
    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext extensionContext) {
        // 1. 在这里要去找到 DataDriver 所指定的 path 下的文件,并解析出来
        // 2. 解析出来的数据是 list<T>
        // 3. 那么我们要做的就是将 T -> new DataDriverInvocationContext()
        // 4. 最后将所有 list 中的 T 都转成了 DataDriverInvocationContext 之后，整体装进 Stream 返回

        // 拿到了 testMethod
        Method requiredTestMethod = extensionContext.getRequiredTestMethod();

        // 拿到注解的信息
        DataDriver dataDriver = requiredTestMethod.getAnnotation(DataDriver.class);
        // 解析出来的数据
        List<DataEntity> dataEntities = YmlUtils.read(dataDriver.path());
        // 将所有 list 中的 T 都转成了 DataInvocationContext 并返回
        return dataEntities.stream().map(DataInvocationContext::new);
    }

    // TODO: 2021/12/18 估计是这里的问题
    static class DataInvocationContext implements TestTemplateInvocationContext, ParameterResolver {

        private DataEntity dataEntity;

        public DataInvocationContext(DataEntity dataEntity) {
            this.dataEntity = dataEntity;
        }

        @Override
        public String getDisplayName(int invocationIndex) {
            return "data driver:" + invocationIndex;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
            return Lists.newArrayList(this);
        }

        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

            // 不管三七二十一全部返回模板
            // return true;

            return extensionContext.getTestMethod()
                    .filter(method -> AnnotationSupport.isAnnotated(method, DataDriver.class))
                    .isPresent();
        }

        /**
         * 将取出的参数数据，塞到方法的 @DataParam 注解内
         * 用个匿名内部类实现 TestTemplateInvocationContext 接口和 ParameterResolver
         * ParameterResolver 值的是处理参数的接口，不实现的话会报：No ParameterResolver registered for parameter
         *
         * @param parameterContext 这里面是参数的类型以及参数名
         * @param extensionContext 这里是测试方法名称
         * @return
         * @throws ParameterResolutionException
         */
        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            // 在这里完成每一个数据到参数的转化，比如从资源文件中拿到的数据是 key = value ，value = zhangsan
            // 那么我们要做的事就是找参数名是 name 的，然后返回 zhangsan
            Parameter parameter = parameterContext.getParameter();
            DataParam dataParam = parameter.getAnnotation(DataParam.class);
            String value = dataParam.value();
            if (dataEntity.isKeyExist(dataParam.value())) {
                // 就是我们需要去做数据设置的
                DataEntity.Entity entity = dataEntity.getEntity(dataParam.value());
                if (entity.isJavaBean()) {
                    // 如果是 bean 的话，就将 entity 的值转化成对应的对象出去
                    return JSON.parseObject(entity.getVal(), parameter.getType());
                } else {
                    // StaticLog.info("基本数据类型");
                    // 这里需不需要转化呢？fastjson 取决于fastjson能不能将值转化成基本数据类型(答案是可以的……所以这个方法是可以省略的).而且 yaml 貌似是可以规定基本的数据类型的，所以可以规避
                    return parseToJavaType(entity.getVal(), parameter.getType());
                }
            } else {
                throw new IllegalStateException("参数" + value + " 在文件内找不到");
            }
        }

        private Object parseToJavaType(String val, Class<?> type) {
            switch (type.getName()) {

                case "java.lang.String":
                    return String.valueOf(val);
                case "java.lang.Integer":
                    return Integer.valueOf(val);
                case "java.lang.Long":
                    return Long.valueOf(val);
                case "java.lang.Boolean":
                    return Boolean.valueOf(val);
                default:
                    throw new IllegalArgumentException(val + "have not support type :" + type.getName());
            }
        }
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
    @Test
    public void testFastJson() {
        String str = "123";
        Integer integer = JSON.parseObject(str, Integer.class);
        System.out.println("integer = " + integer);
    }

}
