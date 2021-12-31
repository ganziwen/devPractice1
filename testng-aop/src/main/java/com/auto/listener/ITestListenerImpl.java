package com.auto.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-16:14
 */
public class ITestListenerImpl implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("onTestStart");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess");
    }

    @Override

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure");
    }

    @Override

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped");
    }

    @Override

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage");
    }

    @Override

    public void onStart(ITestContext context) {
        System.out.println("onStart");
    }

    @Override

    public void onFinish(ITestContext context) {
        System.out.println("onFinish");
    }
}
