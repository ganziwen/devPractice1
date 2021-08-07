package com.learn.springboot.jdbc.dao;

import com.learn.springboot.jdbc.Account;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AccountDao
 * @Description
 * @date 2021/8/7 21:14
 */
public interface AccountDao {
    Account selectByAccountId(String accountId);

    List<Account> selectAll();

    Integer insertAccount(Account account);

}
