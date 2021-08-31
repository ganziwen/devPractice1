package com.example.mysqlschemasync;

import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.SchemaMapper;
import com.example.mysqlschemasync.model.ConnectInfo;
import com.example.mysqlschemasync.model.SchemataDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/31-20:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {

    /**
     * 测试数据库连接
     */
    @Test
    public void testSchema() {
        ConnectInfo connectInfo = new ConnectInfo();
        connectInfo.setUrl("jdbc:mysql://www.xiaowenshu.cn:3388");
        connectInfo.setUserName("root");
        connectInfo.setPassWord("123456");
        List<SchemataDo> schemataDos = DaoFacade.ofMapper(connectInfo, SchemaMapper.class, SchemaMapper::selectAllSchame);
        schemataDos.forEach(System.out::println);
    }
}
