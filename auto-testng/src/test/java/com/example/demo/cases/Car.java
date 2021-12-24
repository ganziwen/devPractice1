package com.example.demo.cases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Car
 * @Description
 * @date 2021/12/24 18:40
 */
public class Car {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Car BeforeClass");
    }

    @Test
    public void carTest() {
        System.out.println("Car Test");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Car AfterClass");
    }
}
