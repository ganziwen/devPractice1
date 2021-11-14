package com.example.autoframework.extention;

import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.extention.engine.CaseGroupDiscoveryFilter;
import com.example.autoframework.extention.engine.CaseTagDiscoveryFilter;
import com.example.autoframework.util.RequiredUtils;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseSelectExtension
 * @Description 用例执行前的拦截
 * @date 2021/11/14 14:46
 */
public class CaseSelectExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        CaseSelector caseSelector = invalidSelector(testMethod.getAnnotation(CaseSelector.class));


        LauncherDiscoveryRequest launcherDiscoveryRequest = LauncherDiscoveryRequestBuilder
                .request()
                .selectors(DiscoverySelectors.selectPackage(caseSelector.scanPackage())) // 这里是基于包来选择，其实还可以基于文件等，但是基本我们使用包居多
                .filters(new CaseTagDiscoveryFilter(caseSelector), new CaseGroupDiscoveryFilter(caseSelector)) // 筛选完包之后筛选 tag
                .build();
        LauncherFactory.create().execute(launcherDiscoveryRequest);


    }

    public CaseSelector invalidSelector(CaseSelector caseSelector) {
        RequiredUtils.requiredNotNull(caseSelector, "case selector should not be null!");
        RequiredUtils.requireNotNullOrEmpty(caseSelector.scanPackage().trim(), "scan package should not be null or empty!");

        // TODO key 和 val 必须成对存在,异常未报出来，分支是能走进来的
        if (!caseSelector.key().trim().isEmpty() ^ !caseSelector.val().trim().isEmpty()) {
            System.out.println("key 和 val 有一个为空");
            RequiredUtils.requireNotNullOrEmpty(caseSelector.val().trim(), "key val should not be null or empty!");
        }
        return caseSelector;
    }


}
