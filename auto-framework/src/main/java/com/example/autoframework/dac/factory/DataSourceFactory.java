package com.example.autoframework.dac.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.autoframework.util.YmlUtils;
import com.google.common.collect.Maps;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Driver;
import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DataSourveFactory
 * @Description
 * @date 2021/12/22 14:23
 */
public final class DataSourceFactory {

    private final Map<String, DataSource> dataSourceMap;


    public DataSourceFactory() {
        this.dataSourceMap = Maps.newConcurrentMap();
    }

    private static class ClassHolder {
        private static final DataSourceFactory HOLDER = new DataSourceFactory();
    }

    public static DataSourceFactory of() {
        return ClassHolder.HOLDER;
    }


    public DataSource getDataSource() {
        Map<String, String> mysqlConfigMap = YmlUtils.readForMap("config/dev.yml");
        System.out.println("mysqlConfigMap = " + mysqlConfigMap);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(mysqlConfigMap.get("mysql-url"));
        druidDataSource.setUsername(mysqlConfigMap.get("mysql-username"));
        druidDataSource.setPassword(mysqlConfigMap.get("mysql-password"));
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
        return druidDataSource;
    }

    @Test
    public void testRes() {
        DataSource dataSource = DataSourceFactory.of().getDataSource();
        System.out.println("dataSource = " + dataSource);
    }

}



