package jdbc;

import com.sun.xml.internal.bind.v2.TODO;

import java.sql.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/14-11:22
 */
public class JdbcCURD {
    static String url = "jdbc:mysql://www.xiaowenshu.cn:3388/test_table?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    static String userName = "root";
    static String password = "123456";

    public static void main(String[] args) {
        testInsert();

    }

    /**
     * 新增,只会返回影响的行数,其实插入修改和删除都是用这个,只是改一下 sql 而已
     */
    public static void testInsert() {

        String sql = "INSERT INTO `tb_user` (`user_id`,`user_name`) VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 4);
            statement.setString(2, "maliu");
            // 注意这里有个深坑,假设是用预编译的写法,方法内不用强制写sql
            int effectRows = statement.executeUpdate();
            System.out.println("effectRows = " + effectRows);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static void testDelete() {
    }

    public static void testUpdate() {
    }

    public static void testQuery() {
    }
}
