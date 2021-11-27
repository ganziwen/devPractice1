package com.example.autoframework.alarm;

import com.example.autoframework.annotation.DingTalkAlarm;
import com.example.autoframework.model.FailureResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/25-16:24
 * 单个 case 执行出现异常才会走到这个处理器
 */
public class AlarmExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        // System.out.println("throwable = " + throwable);
        // System.out.println("这里将消息发送给钉钉");

        Method testMethod = context.getRequiredTestMethod();
        Class<?> testClasses = context.getRequiredTestClass();
        String parameterTypes = Arrays.stream(testMethod.getParameterTypes()).map(Class::getName).collect(Collectors.joining(","));
        DingTalkAlarm dingTalkAlarm = testMethod.getAnnotation(DingTalkAlarm.class);

        // 单个方法也会回调
        FailureResult failureResult = FailureResult.builder()
                .className(testClasses.getName())
                .methodName(testMethod.getName())
                .parameterTypes(parameterTypes)
                .token(dingTalkAlarm.token())
                .build();
        AlarmFacade.doAlarm(failureResult);
    }
}
