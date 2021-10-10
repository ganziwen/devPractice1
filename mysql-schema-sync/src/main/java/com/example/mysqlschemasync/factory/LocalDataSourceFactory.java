package com.example.mysqlschemasync.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.mysqlschemasync.model.ConnectInfo;
import com.google.common.collect.Maps;
import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LocalDataSourceFactory
 * @Description
 * @date 2021/8/29 17:59
 */
public class LocalDataSourceFactory {
    private final Map<String, DataSource> dataSourceMap;

    private LocalDataSourceFactory() {
        this.dataSourceMap = Maps.newConcurrentMap();
    }

    private static class ClassHolder {
        private static final LocalDataSourceFactory holder = new LocalDataSourceFactory();
    }

    public static LocalDataSourceFactory of() {
        return ClassHolder.holder;
    }


    /**
     * druid 的一些配置,因为毕竟是一个服务,自己玩当然没必要,返回的是 DataSource 连接池
     * https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE
     *
     * @param connectInfo
     * @return
     */
    public DataSource getDataSource(ConnectInfo connectInfo) {
        String sourceKey = getDataSourceKey(connectInfo);
        if (this.dataSourceMap.containsKey(sourceKey)) {
            return this.dataSourceMap.get(sourceKey);
        }

        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl(connectInfo.getUrl());
        druidDataSource.setUsername(connectInfo.getUserName());
        druidDataSource.setPassword(connectInfo.getPassWord());
        druidDataSource.setDriverClassName(Driver.class.getName());
        // druidDataSource.setFilters("stat");
        druidDataSource.setMaxActive(20);
        druidDataSource.setInitialSize(1);
        druidDataSource.setMaxWait(6000);
        druidDataSource.setMinIdle(1);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxOpenPreparedStatements(20);
        druidDataSource.setAsyncInit(true);

        this.dataSourceMap.put(sourceKey, druidDataSource);

        return druidDataSource;
    }

    private String getDataSourceKey(ConnectInfo connectInfo) {
        return connectInfo.toString();
    }
}
