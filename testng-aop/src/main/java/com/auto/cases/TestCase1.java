package com.auto.cases;


import com.auto.annotations.Api;
import com.auto.annotations.NetWork;
import com.auto.annotations.TestData;
import com.auto.utils.BaseCase;
import jdk.jfr.events.ThrowablesEvent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-14:09
 */

public class TestCase1 extends BaseCase {

    @Test
    public void test1() {
        System.out.println("TestCase1.test1");
        Assertions.assertThat(1).isEqualTo(2);
    }

    @Api(address = "aaa/bbb/ccc", method = "post", url = "cccc")
    @NetWork(delayed = "1000")
    @TestData(clazz = TestData.class, method = "test")
    @Test
    public void test2() {
        Thread thread = new Thread();
        String methodName = thread.getStackTrace()[1].getMethodName();
        System.out.println("TestCase1.test1");
        Assertions.assertThat(1).isEqualTo(2);
    }
}
