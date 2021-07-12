package jdbc;

import java.sql.*;

/**
 * jdbc 第一个示例
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/12-11:44
 */
public class Demo1 {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://www.xiaowenshu.cn:3388/test_table?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "123456";

        // 建立连接池
        Connection connection = DriverManager.getConnection(url, userName, password);
        String sql = "SELECT * from tb_user";

        // 建立声明(类似 Py 的游标)
        Statement statement = connection.createStatement();

        // 执行查询
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getLong("id"));
            System.out.println(resultSet.getString("user_name"));
        }
        // 一级一级关闭;其实直接关闭 connection 即可,其他俩直接会被关闭
        resultSet.close();
        statement.close();
        connection.close();
    }
}
