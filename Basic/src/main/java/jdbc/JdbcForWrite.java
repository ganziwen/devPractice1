package jdbc;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * jdbc 的统一写方法
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/14-14:05
 */
public class JdbcForWrite {
    static String url = "jdbc:mysql://www.xiaowenshu.cn:3388/test_table?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    static String userName = "root";
    static String password = "123456";

    public static void main(String[] args) {
        testDelete();
        testInsert();
        testUpdate();
    }

    /**
     * 更新数据库的公有方法
     * 还可以完善: ? 的个数和参数的个数要匹配
     *
     * @param sql
     * @param params
     * @return
     */
    public static int modify(String sql, List<Object> params) {

        try (Connection connection = DriverManager.getConnection(url, userName, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            // 注意这里有个深坑,假设是用预编译的写法,方法内不用强制写sql
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void testInsert() {
        String sql = "INSERT INTO `tb_user` (`user_id`,`user_name`,`email`) VALUES (?,?,?);";
        List<Object> params = Lists.newArrayList();
        params.add(5);
        params.add("haha1");
        params.add("haha.@qq.com");
        System.out.println("modify(sql, list) = " + modify(sql, params));
    }

    public static void testUpdate() {
        String sql = "UPDATE `tb_user` SET `email` = ? WHERE user_name in (?)";
        List<Object> params = Lists.newArrayList();
        params.add("haha1.@qq.com111");
        params.add("haha1");
        System.out.println("modify(sql, list) = " + modify(sql, params));
    }

    public static void testDelete() {
        String sql = "DELETE FROM `tb_user` WHERE id in (?)";
        List<Object> params = Lists.newArrayList();
        params.add(5);
        System.out.println("modify(sql, list) = " + modify(sql, params));
    }
}
