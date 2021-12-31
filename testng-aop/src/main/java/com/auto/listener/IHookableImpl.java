package com.auto.listener;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.internal.ConstructorOrMethod;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-16:03
 */
public class IHookableImpl implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        ConstructorOrMethod method = iTestResult.getMethod().getConstructorOrMethod();
        String name = method.getName();
        System.out.println("测试method是 " + name);
        System.out.println("开始执行~");
        //测试用例开始执行
        iHookCallBack.runTestMethod(iTestResult);
        System.out.println("结束~");
    }
}
