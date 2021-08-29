package com.example.mysqlschemasync.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.mysqlschemasync.model.ConnectInfo;
import com.mysql.cj.jdbc.Driver;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import javax.sql.DataSource;
import java.util.Date;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LocalDataSourceFactory
 * @Description
 * @date 2021/8/29 17:59
 */
public class LocalDataSourceFactory {
    private LocalDataSourceFactory() {

    }

    private static class ClassHolder {
        private static final LocalDataSourceFactory holder = new LocalDataSourceFactory();
    }

    public static LocalDataSourceFactory of() {
        return ClassHolder.holder;
    }


    public DataSource getDataSource(ConnectInfo connectInfo) {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl(connectInfo.getUrl());
        druidDataSource.setUsername(connectInfo.getUserName());
        druidDataSource.setPassword(connectInfo.getPassWord());
        druidDataSource.setDriverClassName(Driver.class.getName());

        return druidDataSource;
    }
}
