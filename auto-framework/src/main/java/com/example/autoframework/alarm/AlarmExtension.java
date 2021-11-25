package com.example.autoframework.alarm;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/25-16:24
 * 单个 case 执行出现异常才会走到这个处理器
 */
public class AlarmExtension implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        System.out.println("throwable = " + throwable);
        System.out.println("这里将消息发送给钉钉");
    }
}
