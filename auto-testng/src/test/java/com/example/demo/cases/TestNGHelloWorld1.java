package com.example.demo.cases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TestNGHelloWorld1
 * @Description
 * @date 2021/12/24 18:44
 */
public class TestNGHelloWorld1 {
    @BeforeTest
    public void bfTest() {
        System.out.println("TestNGHelloWorld1 beforTest!");
    }

    @Test(description = "test",enabled = true)
    public String helloWorldTest1() {
        System.out.println("TestNGHelloWorld1 Test1!");
        return "@Test return!";
    }

    @AfterTest
    public void AfTest() {
        System.out.println("TestNGHelloWorld1 AfterTest!");
    }
}
