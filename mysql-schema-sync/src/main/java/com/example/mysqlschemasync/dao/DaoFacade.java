package com.example.mysqlschemasync.dao;

import com.example.mysqlschemasync.factory.LocalSqlSessionFactory;
import com.example.mysqlschemasync.mapper.BaseMapper;
import com.example.mysqlschemasync.model.ConnectInfo;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;

import java.util.function.Function;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DaoFacade
 * @Description 门面设计模式
 * @date 2021/8/29 17:31
 */
@NoArgsConstructor
public class DaoFacade {
    public static <R, M extends BaseMapper> R ofMapper(ConnectInfo connectInfo, Class<M> mapperClazz, Function<M, R> function) {
        try (SqlSession sqlSession = LocalSqlSessionFactory.of().getSqlSession(connectInfo)) {

            M mapper = sqlSession.getMapper(mapperClazz);
            return function.apply(mapper);
        } catch (Exception e) {
            throw new IllegalStateException("exec mapper failed", e);
        }
    }
}
