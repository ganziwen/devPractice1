package jdbc;

import com.mysql.cj.jdbc.result.ResultSetImpl;

import java.io.Reader;
import java.sql.*;

/**
 * 相对规范的写法(补充异常信息)
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/13-15:26
 */
public class Demo2 {
    public static void main(String[] args) {
        testNormalException();
        testTryWithSourceException();

    }

    /**
     * 常规的版本
     */
    public static void testNormalException() {
        String url = "jdbc:mysql://www.xiaowenshu.cn:3388/test_table?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "123456";
        String sql = "SELECT * from tb_user";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // 1. 建立连接
            connection = DriverManager.getConnection(url, userName, password);

            // 2. 具体操作
            // 2.1 预编译 sql
            statement = connection.prepareStatement(sql);

            // 2.2 执行查询
            resultSet = statement.executeQuery(sql);

            // 2.3 处理结果集
            while (resultSet.next()) {
                // 按照列名去查
                System.out.println(resultSet.getLong("id"));

                // 按照列索引去查,从 1 开始(没啥用,还是列名来的直观)
                // System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // 3. 资源关闭：一级一级关闭;其实直接关闭 connection 即可,其他俩直接会被关闭
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * try-with-source 版本
     */
    public static void testTryWithSourceException() {
        String url = "jdbc:mysql://www.xiaowenshu.cn:3388/test_table?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "123456";
        String sql = "SELECT * from tb_user";


        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("user_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
