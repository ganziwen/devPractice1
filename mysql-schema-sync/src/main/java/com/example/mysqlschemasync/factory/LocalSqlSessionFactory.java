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
     * 构造 session ,mybaties 的配置肯定不能写到配置文件内,根据传入的数据库链接信息,得到一个 SqlSession,根据 SqlSession  再给其一个 mapper ,就能进行操作sql语句了
     *
     * @param connectInfo
     * @return
     */
    public SqlSession getSqlSession(ConnectInfo connectInfo) {
        Configuration configuration = new Configuration();
        // 相当于 mabaties-config.xml 的 mappers 标签
        configuration.addMappers("com.example.mysqlschemasync.mapper");

        // 设置驼峰.相当于 mabaties-config.xml 的 settings 标签
        configuration.setMapUnderscoreToCamelCase(true);

        // 利用门面设计模式,我们需要规定数据库连接的 datasource 相关配置
        Environment environment = new Environment.Builder("common")
                .dataSource(LocalDataSourceFactory.of().getDataSource(connectInfo))
                .transactionFactory(new JdbcTransactionFactory())
                .build();
        // 相当于 mabaties-config.xml 的 environments 标签
        configuration.setEnvironment(environment);

        // mybaties 本身自己支持使用 configuration 构建一个 SqlSessionFactory,其实本质上什么xml都是这样构建出来的,可以去看一下 build 的 代码
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sessionFactory.openSession();
    }
}
