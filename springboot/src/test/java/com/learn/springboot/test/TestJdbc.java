package com.learn.springboot.test;

import com.learn.springboot.jdbc.Account;
import com.learn.springboot.jdbc.dao.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TestJdbc
 * @Description
 * @date 2021/8/7 21:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJdbc {
    @Autowired
    private AccountDao accountDao;

    @Test
    public void testSelectByAccountId() {
        Account account = accountDao.selectByAccountId("11");
        System.out.println("account = " + account);
    }
}
