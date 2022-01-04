package com.auto.listener;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-16:03
 */
public class IHookableImpl implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
        // iTestResult.setParameters(new Object[]{"testiest"});
        iTestResult.setTestName("更改测试名称");
        Object[][] objects = new Object[][]{
                {"Cedric222", new Integer(36)},
                {"Anne333", new Integer(37)},
        };

        // System.out.printf("参数:[%s]%n", Arrays.stream(iHookCallBack.getParameters()).peek(entry -> entry.toString()).collect(Collectors.toList()));
        iTestResult.setParameters(objects);
        String name = method.getName();
        System.out.printf("开始执行测试方法:[%s]%n", name);
        System.out.printf("测试名称:[%s]%n", iTestResult.getTestName());
        System.out.println(Arrays.toString(iHookCallBack.getParameters()));
        //测试用例开始执行
        iHookCallBack.runTestMethod(iTestResult);

        System.out.printf("结束执行测试方法:[%s]%n", name);
    }
}
