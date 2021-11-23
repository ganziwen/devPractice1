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
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseEngineExtension
 * @Description
 * @date 2021/11/13 12:16
 */
public class CaseEngineExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        // 创建一个 caseSelector
        CaseSelector caseSelector = invalidSelector(testMethod.getAnnotation(CaseSelector.class));


        // TODO: 2021/11/20 这里加入了 group 的过滤器就跑不过了，需要看下是什么问题导致的
        LauncherDiscoveryRequest launcherDiscoveryRequest = LauncherDiscoveryRequestBuilder
                .request()
                // 这里是基于包来选择，其实还可以基于文件等，但是基本我们使用包居多
                .selectors(DiscoverySelectors.selectPackage(caseSelector.scanPackage()))
                // .filters(new CaseDiscoveryFilter(caseSelector))
                // 筛选完包之后筛选 tag,这里可以加入多过滤器，比如加入 tag 的再加入 group 的
                .filters(new CaseTagDiscoveryFilter(caseSelector))
                .filters(new CaseGroupDiscoveryFilter(caseSelector))
                .build();


        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // listener 可以统计到用例的执行信息，可以拿来统计报告
        LauncherFactory.create().execute(launcherDiscoveryRequest, listener);
        TestExecutionSummary summary = listener.getSummary();
        // System.out.println("summary.getTestsFailedCount() = " + summary.getTestsFailedCount());
        // System.out.println("summary.getTestsFoundCount() = " + summary.getTestsFoundCount());
        // System.out.println("summary.getTestsStartedCount() = " + summary.getTestsStartedCount());
        // System.out.println("summary.getTestsSkippedCount() = " + summary.getTestsSkippedCount());
        // System.out.println("summary.getTestsSucceededCount() = " + summary.getTestsSucceededCount());


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
