package com.example.demo.cases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName BMW
 * @Description
 * @date 2021/12/24 18:41
 */
public class BMW extends Car{

    @Test
    public void BMWTest() {
        System.out.println("BMW Test");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("BMW AfterClass");
    }
}
