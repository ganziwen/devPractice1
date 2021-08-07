package com.learn.springboot.jdbc.dao.impl;

import com.learn.springboot.jdbc.Account;
import com.learn.springboot.jdbc.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AccountDaoImpl
 * @Description
 * @date 2021/8/7 21:16
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectByAccountId(String accountId) {

        String sql = "select * from t_student where id=?";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
        Account account = jdbcTemplate.queryForObject(sql, new Object[]{accountId}, rowMapper);
        return account;
    }

    @Override
    public List<Account> selectAll() {
        String sql = "select * from t_student";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<Account>(Account.class);
        List<Account> query = jdbcTemplate.query(sql, rowMapper);
        return query;
    }

    @Override
    public Integer insertAccount(Account account) {
        String sql = "insert into t_student values(?,?,?)";
        int n = jdbcTemplate.update(sql, account.getId(), account.getAccountId(), account.getAccountName());
        return n;
    }
}
