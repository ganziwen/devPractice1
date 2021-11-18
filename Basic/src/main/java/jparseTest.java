import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import org.junit.jupiter.api.Test;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName jparse
 * @Description
 * @date 2021/9/11 12:39
 */

public class jparseTest {


    @Test
    public void testParse() throws SQLSyntaxErrorException {


        String sql = "CREATE TABLE `tb_user` (\n" +
                "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',\n" +
                "  `user_id` varchar(64) NOT NULL DEFAULT '' COMMENT '唯一id',\n" +
                "  `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',\n" +
                "  `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮件',\n" +
                "  `address` varchar(256) NOT NULL DEFAULT '' COMMENT '地址',\n" +
                "  `corp` varchar(64) NOT NULL DEFAULT '' COMMENT '公司',\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY `uniq_user_id` (`user_id`),\n" +
                "  KEY `idx_user_name` (`user_name`),\n" +
                "  KEY `idx_email_address` (`email`,`address`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表'";
        String dbType = "mysql";
        System.out.println("原始SQL 为 ： " + sql);


        SQLStatement statement = parser(sql, dbType);


        final SQLCreateTableStatement sqlCreateTableStatement = (SQLCreateTableStatement) parser(sql, dbType);
        // final SQLCreateTableParser sqlCreateTableParser = (SQLCreateTableParser) parser(sql, dbType);
        System.out.println(sqlCreateTableStatement.getTableElementList());
        sqlCreateTableStatement.getTableElementList().forEach(col -> System.out.println(col));
        sqlCreateTableStatement.getColumn("");
    }


    public static SQLStatement parser(String sql, String dbType) throws SQLSyntaxErrorException {
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);
        if (list.size() > 1) {
            throw new SQLSyntaxErrorException("MultiQueries is not supported,use single query instead ");
        }
        return list.get(0);
    }


}
