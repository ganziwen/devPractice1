package com.example.autoframework.demo1;

import org.junit.jupiter.api.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/11-17:43
 */
public class Junit5Demo {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @Test
    @DisplayName("测试用例1")
    public void testCase1() {
        System.out.println("===testCase1===");
    }

    @Test
    @DisplayName("测试用例2")
    public void testCase2() {
        System.out.println("===testCase2===");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

}
