package com.example.autoframework.engine;

import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.engine.filter.CaseGroupDiscoveryFilter;
import com.example.autoframework.engine.filter.CaseTagDiscoveryFilter;
import com.example.autoframework.util.RequiredUtils;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseEngineExtension
 * @Description
 * @date 2021/11/13 12:16
 */
// TODO: 2021/11/25 比较大的 bug 是有异常不会进行捕获住,这里要看下是什么问题
public class CaseEngineExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        // 创建一个 caseSelector
        CaseSelector caseSelector = invalidSelector(testMethod.getAnnotation(CaseSelector.class));

        // 构造发现请求的实体
        LauncherDiscoveryRequest launcherDiscoveryRequest = LauncherDiscoveryRequestBuilder
                .request()
                // 选择器：这里是基于包名字来选择,其实还可以基于文件等,但是基本我们使用包居多
                .selectors(DiscoverySelectors.selectPackage(caseSelector.scanPackage()))
                // .filters(new CaseDiscoveryFilter(caseSelector))
                // 过滤器：筛选完包之后筛选 tag,这里可以加入多过滤器,比如加入 tag 的再加入 group 的
                .filters(new CaseTagDiscoveryFilter(caseSelector))
                .filters(new CaseGroupDiscoveryFilter(caseSelector))
                .build();

        //   信息结果收集的监听器
        // TODO: 2021/11/26 这里的被注释掉的话，就啥都不会返回了，需要着重研究下，而且这里我估计跟异常返回没有捕获是有关的，需要倒带回去看下
        SummaryGeneratingListener listener = new SummaryGeneratingListener();


        // listener 可以统计到用例的执行信息,可以拿来统计报告
        LauncherFactory.create().execute(launcherDiscoveryRequest, listener);

        // TestExecutionSummary summary = listener.getSummary();
        // System.out.println("summary.getTestsFailedCount() = " + summary.getTestsFailedCount());
        // System.out.println("summary.getTestsFoundCount() = " + summary.getTestsFoundCount());
        // System.out.println("summary.getTestsStartedCount() = " + summary.getTestsStartedCount());
        // System.out.println("summary.getTestsSkippedCount() = " + summary.getTestsSkippedCount());
        // System.out.println("summary.getTestsSucceededCount() = " + summary.getTestsSucceededCount());
    }

    public CaseSelector invalidSelector(CaseSelector caseSelector) {
        RequiredUtils.requiredNotNull(caseSelector, "case selector should not be null!");
        // 这里要校验一下包名的合法性
        RequiredUtils.requireNotNullOrEmpty(caseSelector.scanPackage().trim(), "scan package should not be null or empty!");

        // TODO key 和 val 必须成对存在,异常未报出来,分支是能走进来的;team 和 group 也是要成对存在
        if (!caseSelector.key().trim().isEmpty() ^ !caseSelector.val().trim().isEmpty()) {
            System.out.println("key 和 val 有一个为空");
            RequiredUtils.requireNotNullOrEmpty(caseSelector.val().trim(), "key val should not be null or empty!");
        }
        return caseSelector;
    }

}
