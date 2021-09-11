package com.example.mysqlschemasync.model;

import lombok.Data;

import java.sql.Connection;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/9-20:58
 * 差异出来的类
 */
@Data
public class DifferenceInfo {
    private String sql;
    private ConnectInfo connectInfo;
    private String dataBase;
    private String table;
}
