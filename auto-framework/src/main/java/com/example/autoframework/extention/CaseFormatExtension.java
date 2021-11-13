package com.example.autoframework.extention;

import com.example.autoframework.extention.format.FormatManager;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseFormateExtension
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
