package com.auto.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-15:49
 * 自定义测试套监听器
 */
public class ISuiteListenerImpl implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        System.out.println("suite onStart!");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("suite onFinish!");
    }
}
