package com.auto.utils;

import com.auto.listener.IHookableImpl;
import com.auto.listener.ISuiteListenerImpl;
import com.auto.listener.ITestListenerImpl;
import org.testng.annotations.Listeners;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-15:18
 */
@Listeners({IHookableImpl.class, ISuiteListenerImpl.class, ITestListenerImpl.class})
public class BaseCase {
}
