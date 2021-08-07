package com.learn.springboot.test;

import com.learn.springboot.params.Pay;
import com.sun.corba.se.spi.orb.ParserImplBase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.portable.ValueOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import java.awt.image.VolatileImage;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TestController
 * @Description
 * @date 2021/8/7 17:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);


    @Autowired
    private Pay pay;

    @Before
    public void init() {
        logger.info("Before");
    }

    @BeforeClass
    public static void beforeClass() {
        logger.info("BeforeClass");
    }

    @Test
    public void testPay31() {
        String s = pay.pay3("123456");
        System.out.println("s = " + s);
    }

    @Test
    public void testPay32() {
        String s = pay.pay3("123456");
        System.out.println("s = " + s);
    }

    @After
    public void after() {
        logger.info("After");
    }

    @AfterClass
    public static void afterClass() {
        logger.info("AfterClass");
    }

}

