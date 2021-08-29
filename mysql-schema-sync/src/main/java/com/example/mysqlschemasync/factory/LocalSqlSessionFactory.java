package com.example.mysqlschemasync.factory;

import com.example.mysqlschemasync.model.ConnectInfo;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LocalSQLSessionFactory
 * @Description
 * @date 2021/8/29 17:38
 */
public final class LocalSqlSessionFactory {
    private LocalSqlSessionFactory() {

    }

    private static class ClassHolder {
        private static final LocalSqlSessionFactory holder = new LocalSqlSessionFactory();
    }

    public static LocalSqlSessionFactory of() {
        return ClassHolder.holder;
    }


    public SqlSession getSqlSession(ConnectInfo connectInfo) {
        Configuration configuration = new Configuration();
        configuration.addMappers("com.example.mysqlschemasync.mapper");

        Environment environment = new Environment.Builder("common").dataSource(LocalDataSourceFactory.of().getDataSource(connectInfo)).build();
        configuration.setEnvironment(environment);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sessionFactory.openSession();
    }
}
