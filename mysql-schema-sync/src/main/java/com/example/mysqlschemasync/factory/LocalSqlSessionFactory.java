package com.example.mysqlschemasync.factory;

import com.example.mysqlschemasync.model.ConnectInfo;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

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


    /**
     * 构造 session ,mybaties 的配置肯定不能写到配置文件内,根据传入的数据库链接信息,得到一个 SqlSession,得到之后就可以 curd 了
     *
     * @param connectInfo
     * @return
     */
    public SqlSession getSqlSession(ConnectInfo connectInfo) {
        Configuration configuration = new Configuration();
        // 相当于 mabaties xml 的 mapper 包
        configuration.addMappers("com.example.mysqlschemasync.mapper");

        // 设置驼峰
        configuration.setMapUnderscoreToCamelCase(true);

        Environment environment = new Environment.Builder("common")
                .dataSource(LocalDataSourceFactory.of().getDataSource(connectInfo))
                .transactionFactory(new JdbcTransactionFactory())
                .build();
        configuration.setEnvironment(environment);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sessionFactory.openSession();
    }
}
