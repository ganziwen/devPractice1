package com.development.mock.cases;

import com.development.mock.controller.MockController2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SpringTest
 * @Description
 * @date 2022/1/14 23:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

    @Autowired
    private MockController2 mockController2;

    @Test
    public void testMockReg() {
        mockController2.doMock();
    }

}
