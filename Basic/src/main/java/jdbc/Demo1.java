package jdbc;

import java.sql.*;

/**
 * jdbc 第一个示例,基本操作流程
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
        String sql = "SELECT * from tb_user";

        // 1. 建立连接
        Connection connection = DriverManager.getConnection(url, userName, password);

        // 2. 具体操作
        // 2.1 预编译 sql
        PreparedStatement statement = connection.prepareStatement(sql);

        // 2.2 执行查询
        ResultSet resultSet = statement.executeQuery();

        // 2.3 处理结果集
        while (resultSet.next()) {
            // 按照列名去查
            System.out.println(resultSet.getLong("id"));
            // 按照列索引去查,从 1 开始(没啥用,还是列名来的直观)
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString("user_name"));
        }
        // 3. 资源关闭：一级一级关闭;其实直接关闭 connection 即可,其他俩直接会被关闭
        resultSet.close();
        statement.close();
        connection.close();
    }
}
