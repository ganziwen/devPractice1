package com.example.autoframework.profile;

import com.example.autoframework.annotation.EnvProfile;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ProfileExtension
 * @Description
 * @date 2021/12/23 10:59
 */
public class ProfileExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        EnvProfile profile = testMethod.getAnnotation(EnvProfile.class);
        ProfileHolder.of().setProfile(profile.value());
    }
}
