package com.example.mysqlschemasync.dao;

import com.example.mysqlschemasync.factory.LocalSqlSessionFactory;
import com.example.mysqlschemasync.mapper.BaseMapper;
import com.example.mysqlschemasync.mapper.SchemaMapper;
import com.example.mysqlschemasync.model.ConnectInfo;
import com.example.mysqlschemasync.model.SchemataDo;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
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
}
