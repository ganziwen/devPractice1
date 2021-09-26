package com.example.mysqlschemasync;

import com.example.mysqlschemasync.dao.DaoFacade;
import com.example.mysqlschemasync.mapper.ColumnsMapper;
import com.example.mysqlschemasync.mapper.SchemaMapper;
import com.example.mysqlschemasync.mapper.StatisticsMapper;
import com.example.mysqlschemasync.mapper.TablesMapper;
import com.example.mysqlschemasync.model.*;
import org.apache.coyote.http11.filters.VoidOutputFilter;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

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
        connectInfo.setUrl("jdbc:mysql://www.xiaowenshu.cn:3399");
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
    public void testShowTables() {
        Set<ShowTableDO> tablesDos = DaoFacade.ofMapper(connectInfo, TablesMapper.class, TablesMapper -> TablesMapper.showTables("test_table", "tb_user"));
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
    public void testStatistic() {
        Set<StatisticsDo> statisticsDos = DaoFacade.ofMapper(connectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable("test_table", "tb_user"));
        statisticsDos.forEach(System.out::println);
    }

    @Test
    public void testStatisticWithGroupBy() {
        Set<StatisticsDo> statisticsDos = DaoFacade.ofMapper(connectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTableGroupBy("test_table", "tb_user"));
        statisticsDos.forEach(System.out::println);
    }

    @Test
    public void testSqlUpdate() {
        DaoFacade.execSql(connectInfo, "alter table test_table.tb_user modify corp varchar(64) default '' not null comment '公司地址';");

    }

    @Test
    public void testGroupBy() {
        Set<StatisticsDo> statisticsDos = DaoFacade.ofMapper(connectInfo, StatisticsMapper.class, statisticsMapper -> statisticsMapper.selectByTable("test_table", "tb_user"));
        Set<StatisticsDo> collect = statisticsDos.stream().
                // peek(str -> System.out.println("初始的索引" + str.getIndexName() + ":" + str.toString())).
                        collect(Collectors.groupingBy(StatisticsDo::getIndexName)).
                        entrySet().
                        stream().
                // peek(streamsres -> System.out.println("每一个单独的索引进行entry" + streamsres)).
                        map(getSingleColumn -> {
                    List<String> columns = new ArrayList<>();
                    List<Long> seq = new ArrayList<>();

                    getSingleColumn.getValue().stream().sorted(Comparator.comparingInt(o -> o.getSeqInIndex().intValue())).forEach(st -> {
                        columns.add(st.getColumnName());
                        seq.add(st.getSeqInIndex());
                    });
                    getSingleColumn.getValue().get(0).setColumnName(String.join(",", columns));
                    getSingleColumn.getValue().get(0).setSeqInIndex(seq.get(seq.size() - 1));
                    return getSingleColumn.getValue().get(0);
                }).collect(Collectors.toSet());

        collect.forEach(System.out::println);
    }
}
