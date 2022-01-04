package com.auto.listener;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import java.util.Arrays;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-16:03
 */
public class IHookableImpl implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
        iTestResult.setParameters(new Object[]{"testiest"});
        iTestResult.setTestName("更改测试名称");
        String name = method.getName();
        System.out.printf("开始执行测试方法:[%s]%n", name);
        System.out.printf("测试名称:[%s]%n", iTestResult.getTestName());
        System.out.println(Arrays.toString(iHookCallBack.getParameters()));
        //测试用例开始执行
        iHookCallBack.runTestMethod(iTestResult);

        System.out.printf("结束执行测试方法:[%s]%n", name);
    }
}
