package com.example.autoframework.engine;

import com.example.autoframework.format.observer.FormatManager;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * 当单测执行到具体的生命周期节点,会执行此测试类,用例的格式化校验可以放在此处。要实现 callback 这个回调类
 *
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseFormatExtension
 * @Description
 * @date 2021/11/13 11:10
 */

public class CaseFormatExtension implements BeforeTestExecutionCallback {


    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        // 这个 extensionContext.getRequiredTestMethod() 就是具体的每个 test
        FormatManager.INSTANCE.doFormatcheck(extensionContext.getRequiredTestMethod());
    }
}
