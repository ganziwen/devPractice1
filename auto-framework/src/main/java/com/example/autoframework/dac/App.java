package com.example.autoframework.dac;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;
import com.example.autoframework.dac.factory.DataSourceFactory;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description 数据库操作需要再想一下怎么解决问题
 * @date 2021/12/22 13:14
 */
public class App {
    @Test
    public void test1() {
        // 这里面需要一个 dataSource
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceFactory.of().getDataSource());
        HashMap<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("id", 2);
        List<Map<String, Object>> resMaps = jdbcTemplate.queryForList("select * from test_table.tb_account where id = '1';", paramMap);
        for (Map<String, Object> resMap : resMaps) {
            System.out.println("resMap = " + resMap);
        }
    }

    @Test
    public void test2() {
        try {
            List<Entity> tb_account = Db.use().findAll("tb_account");
            for (Entity entity : tb_account) {
                System.out.println("entity.getTableName() = " + entity.getTableName());
                System.out.println("entity.getFieldNames() = " + entity.getFieldNames());
                System.out.println("entity.get(\"id\") = " + entity.get("id"));
                System.out.println("entity.getStr(\"id\") = " + entity.getStr("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void test3() {
        // String sql = "select name, age from t_user where id = 1";
        String sql = "CREATE TABLE `tb_account` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n" +
                "  `ant_id` varchar(64) NOT NULL DEFAULT '' COMMENT 'account id',\n" +
                "  `ant_name` varchar(64) NOT NULL DEFAULT '' COMMENT '账户名称',\n" +
                "  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '账户余额',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `uniq_ant_id` (`ant_id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='账户表'";


        String dbType = "mysql";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        SQLStatement stmt = stmtList.get(0);

        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(DbType.valueOf(dbType));
        stmt.accept(statVisitor);
        System.out.println(statVisitor);

        System.out.println(statVisitor.getTables()); //{t_org=Create}
        System.out.println(statVisitor.getColumns()); // [t_org.fid, t_org.name]
    }
    @Test
    public void test4() {
        // String sql = "select name, age from t_user where id = 1";
        String sql = "CREATE TABLE `tb_account` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n" +
                "  `ant_id` varchar(64) NOT NULL DEFAULT '' COMMENT 'account id',\n" +
                "  `ant_name` varchar(64) NOT NULL DEFAULT '' COMMENT '账户名称',\n" +
                "  `balance` bigint(20) NOT NULL DEFAULT '0' COMMENT '账户余额',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `uniq_ant_id` (`ant_id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='账户表'";


        // 新建 MySQL Parser
        SQLStatementParser parser = new MySqlStatementParser(sql);

        // 使用Parser解析生成AST，这里SQLStatement就是AST
        SQLStatement statement = parser.parseStatement();

        // 使用visitor来访问AST
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);

        // 从visitor中拿出你所关注的信息
        System.out.println(visitor.getColumns());
    }
}
