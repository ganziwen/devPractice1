package com.learn.springboot.jdbc;


import lombok.Data;

import java.awt.print.PrinterAbortException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Account
 * @Description
 * @date 2021/8/7 21:12
 */
@Data
public class Account {
    private Long id;
    private String accountId;
    private String accountName;
}
