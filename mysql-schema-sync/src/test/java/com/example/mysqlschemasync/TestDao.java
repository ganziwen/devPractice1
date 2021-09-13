package com.example.mysqlschemasync;

import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.ColumnsMapper;
import com.example.mysqlschemasync.mapper.SchemaMapper;
import com.example.mysqlschemasync.mapper.StatisticsMapper;
import com.example.mysqlschemasync.mapper.TablesMapper;
import com.example.mysqlschemasync.model.*;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/31-20:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDao {

    ConnectInfo connectInfo;

    @Before
    public void init() {
        connectInfo = new ConnectInfo();
        connectInfo.setUrl("jdbc:mysql://www.xiaowenshu.cn:3388");
        connectInfo.setUserName("root");
        connectInfo.setPassWord("123456");
    }

    /**
     * 测试数据库连接
     */
    @Test
    public void testSchema() {
        Set<SchemataDo> schemataDos = DaoFacade.ofMapper(connectInfo, SchemaMapper.class, SchemaMapper::selectAllSchame);
        schemataDos.forEach(System.out::println);
    }

    @Test
    public void testTable() {
        Set<TablesDo> tablesDos = DaoFacade.ofMapper(connectInfo, TablesMapper.class, TablesMapper -> TablesMapper.selectByTable("test_table", "tb_user"));
        tablesDos.forEach(System.out::println);
    }

    @Test
    public void testColumn() {
        Set<ColumnsDo> columnsDos = DaoFacade.ofMapper(connectInfo, ColumnsMapper.class, columnsMapper -> columnsMapper.selectByTable("test_table", "tb_user"));
        columnsDos.forEach(System.out::println);
    }

    // @Test
    // public void testStatistics() {
    //     Set<StatisticsDo> statisticsDos = DaoFacade.ofMapper(connectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable("test_table", "tb_user"));
    //     statisticsDos.forEach(System.out::println);
    // }

    @Test
    public void testStatisticWithGroupBy() {
        Set<StatisticsDo> statisticsDos = DaoFacade.ofMapper(connectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTableGroupBy("test_table", "tb_user"));
        statisticsDos.forEach(System.out::println);
    }
}
