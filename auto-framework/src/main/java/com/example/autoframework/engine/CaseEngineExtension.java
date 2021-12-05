package com.example.autoframework.engine;

import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.annotation.DingTalkAlarm;
import com.example.autoframework.annotation.ReportConfig;
import com.example.autoframework.engine.filter.CaseGroupDiscoveryFilter;
import com.example.autoframework.engine.filter.CaseTagDiscoveryFilter;
import com.example.autoframework.engine.listener.FailureListener;
import com.example.autoframework.model.FailureInfo;
import com.example.autoframework.model.SummaryResult;
import com.example.autoframework.util.ReflectUtils;
import com.example.autoframework.util.RequiredUtils;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.StringUtils;
import org.junit.platform.engine.TestSource;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.engine.support.descriptor.MethodSource;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.beans.BeanUtils;
import sun.reflect.misc.ReflectUtil;

import java.io.EOFException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Case engine extension.
 *
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseEngineExtension
 * @Description
 * @date 2021 /11/13 12:16
 */
// TODO: 2021/11/25 比较大的 bug 是有异常不会进行捕获住,这里要看下是什么问题
public class CaseEngineExtension implements BeforeTestExecutionCallback {
    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        // 创建一个 caseSelector
        CaseSelector caseSelector = invalidSelector(testMethod.getAnnotation(CaseSelector.class));

        // 构造发现请求的实体,也就是捞取符合条件的待执行 cases
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
        // TODO: 2021/11/26 这里的被注释掉的话,就啥都不会返回了,需要着重研究下,而且这里我估计跟异常返回没有捕获是有关的,需要倒带回去看下
        SummaryGeneratingListener listener = new SummaryGeneratingListener();

        // 根据 @Dingtalk 注解来判断是否加处理失败的监听器
        if (testMethod.isAnnotationPresent(DingTalkAlarm.class)) {
            // 获取 @Dingtalk 的 token 值
            DingTalkAlarm dingTalkAlarm = testMethod.getAnnotation(DingTalkAlarm.class);

            // todo 很奇怪的是，这里的 listener 根本获取不到任何 exception
            // 这里是处理失败的监听器,是我们自定义的.这里只是负责抛出去但不负责处理，处理是在 callback 里面进行处理的
            FailureListener failureListener = new FailureListener(dingTalkAlarm.token(), dingTalkAlarm.callback());

            // listener 可以统计到用例的执行信息,可以拿来统计报告
            // 将 failureListener 塞进来
            Launcher launcher = LauncherFactory.create();
            // launcher.execute(launcherDiscoveryRequest, listener, failureListener);

            // 这两种写法要看下
            launcher.registerTestExecutionListeners(listener);
            launcher.execute(launcherDiscoveryRequest, failureListener);

        } else {
            // 没配置  @Dingtalk
            LauncherFactory.create().execute(launcherDiscoveryRequest, listener);

        }

        // 整个批量结束后才回调，并不是每个方法执行完就回调,这里可以用来进行处理 report 的逻辑
        TestExecutionSummary summary = listener.getSummary();

        // 如果设置了 ReportConfig 注解说明要走我们自己定义好的报告模板
        if (testMethod.isAnnotationPresent(ReportConfig.class)) {
            ReportConfig reportConfig = testMethod.getAnnotation(ReportConfig.class);
            SummaryResult summaryResult = transSummaryResult(summary);
            // 将 ReportConfig 注解上的 token 塞进来
            summaryResult.setToken(reportConfig.token());
            // 利用反射实例化出来 ReportConfig内，callback 写的类的对象，执行对应的 postExecutionSummary 方法
            ReflectUtils.newInstance(reportConfig.callback()).postExecutionSummary(summaryResult);


        }

    }


    private SummaryResult transSummaryResult(TestExecutionSummary summary) {
        SummaryResult summaryResult = new SummaryResult();
        BeanUtils.copyProperties(summary, summaryResult);

        // 将 TestExecutionSummary 的错误list转成我们需要的错误信息
        summaryResult.setFailureInfos(summaryResult.getFailures().stream().map(failure -> {
            TestIdentifier testIdentifier = failure.getTestIdentifier();
            Throwable exception = failure.getException();
            Optional<TestSource> optionalTestSource = testIdentifier.getSource();
            TestSource testSource = optionalTestSource.get();
            MethodSource methodSource = (MethodSource) testSource;
            return FailureInfo.builder()
                    .className(methodSource.getClassName())
                    .methodName(methodSource.getMethodName())
                    .parameterTypes(methodSource.getMethodParameterTypes())
                    .throwable(failure.getException())
                    .build();
        }).collect(Collectors.toList()));

        return summaryResult;
    }

    public CaseSelector invalidSelector(CaseSelector caseSelector) {
        RequiredUtils.requiredNotNull(caseSelector, "case selector should not be null!");
        // 这里要校验一下包名的合法性
        RequiredUtils.requireNotNullOrEmpty(caseSelector.scanPackage().trim(), "scan package should not be null or empty!");

        //  key 和 val 必须成对存在,异常未报出来,分支是能走进来的;team 和 group 也是要成对存在
        if (StringUtils.isNotBlank(caseSelector.key().trim()) ^ StringUtils.isNotBlank(caseSelector.val().trim())) {
            System.out.println("key 和 val 有一个为空");
            RequiredUtils.requireNotNullOrEmpty(caseSelector.val().trim(), "key val should not be null or empty!");
        } else if (StringUtils.isNotBlank(caseSelector.team().trim()) ^ StringUtils.isNotBlank(caseSelector.group().trim())) {
            System.out.println("team 和 group 有一个为空");
            RequiredUtils.requireNotNullOrEmpty(caseSelector.val().trim(), "team group should not be null or empty!");
        }
        return caseSelector;
    }

}
