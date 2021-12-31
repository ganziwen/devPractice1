package com.auto.cases;


import com.auto.utils.BaseCase;
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
}
