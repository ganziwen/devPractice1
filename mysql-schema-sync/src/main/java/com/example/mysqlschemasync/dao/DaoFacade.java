package com.example.mysqlschemasync.dao;

import com.example.mysqlschemasync.factory.LocalSqlSessionFactory;
import com.example.mysqlschemasync.mapper.BaseMapper;
import com.example.mysqlschemasync.model.ConnectInfo;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DaoFacade
 * @Description 门面设计模式, 需要重点理解一下
 * @date 2021/8/29 17:31
 */
@NoArgsConstructor
public class DaoFacade {
    public static <R, T extends BaseMapper> R ofMapper(ConnectInfo connectInfo, Class<T> mapperClazz, Function<T, R> function) {
        // 其实就是做了这一步操作
        // SqlSession sqlSession1 = LocalSqlSessionFactory.of().getSqlSession(connectInfo);
        // 获取的 mapper 是不确定的,所以要泛型化
        // SchemaMapper mapper1 = sqlSession1.getMapper(SchemaMapper.class);
        // 通过一个转化型接口,将 mapper 转成 List<SchemataDo>
        // List<SchemataDo> schemataDos = mapper1.selectAllSchame();

        try (SqlSession sqlSession = LocalSqlSessionFactory.of().getSqlSession(connectInfo)) {
            T mapper = sqlSession.getMapper(mapperClazz);
            return function.apply(mapper);
        } catch (Exception e) {
            throw new IllegalStateException("exec mapper failed", e);
        }
    }

    /**
     * 执行单条 sql
     *
     * @param connectInfo
     * @param sql
     * @return
     */
    public static void execSql(ConnectInfo connectInfo, String sql) {

        try (SqlSession sqlSession = LocalSqlSessionFactory.of().getSqlSession(connectInfo)) {
            sqlSession.getConnection().prepareStatement(sql).execute();
        } catch (Exception e) {
            throw new IllegalStateException("exec mapper failed", e);
        }
    }

    /**
     * 执行 sql,避免产生一堆连接池
     *
     * @param connectInfo
     * @param sqlList
     */
    public static void execSql(ConnectInfo connectInfo, List<String> sqlList) {

        // try (SqlSession sqlSession = LocalSqlSessionFactory.of().getSqlSession(connectInfo); Connection connection = sqlSession.getConnection();) {
        //     for (String sql : sqlList) {
        //         try (PreparedStatement statement = connection.prepareStatement(sql);) {
        //             statement.execute();
        //         }
        //     }
        // } catch (Exception e) {
        //     throw new IllegalStateException("exec mapper failed", e);
        // }
        try (Connection connection = LocalSqlSessionFactory.of().getSqlSession(connectInfo).getConnection();) {
            for (String sql : sqlList) {
                try (PreparedStatement statement = connection.prepareStatement(sql);) {
                    statement.execute();
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("exec mapper failed", e);
        }
    }

    /**
     * 建表语句的 sql 获取
     *
     * @param connectInfo
     * @param schemaName
     * @param tableName
     * @return
     */
    public static String showTable(ConnectInfo connectInfo, String schemaName, String tableName) {
        try (Connection connection = LocalSqlSessionFactory.of().getSqlSession(connectInfo).getConnection()) {
            String sql = String.format("show create table `%s`.`%s`;", schemaName, tableName);
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Create Table");
            } else {
                throw new IllegalStateException("show create table failed");
            }
        } catch (Exception e) {
            throw new IllegalStateException("show create table failed", e);
        }
    }
}
