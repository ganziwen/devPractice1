package tools.apache.commons.beanutils;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/16-20:26
 */
public class TestBeanUtils {
    Users users = new Users();

    Empolyee empolyee = new Empolyee();

    @DisplayName("读取和设置属性值")
    @Test
    public void testProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        BeanUtils.setProperty(users, "username", "zhangsan");
        String username = BeanUtils.getProperty(users, "username");
        System.out.println("username = " + username);
        System.out.println("users= " + users.toString());
        // 描述属性的长度和是否为空
        System.out.println("BeanUtils.describe(username) = " + BeanUtils.describe(username));

    }

    @DisplayName("map和bean的互相转换")
    @Test
    public void testMapConvert() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        users.setUsername("testMapConvert");
        // bean 转 map
        Map<String, String> describe = BeanUtils.describe(users);
        System.out.println("describe.get(\"username\") = " + describe.get("username"));
        System.out.println("describe.get(\"password\") = " + describe.get("password"));

        Users users2 = new Users();
        // map 转 bean
        BeanUtils.populate(users2, describe);
        System.out.println("users2" + users2.toString());
    }

    @DisplayName("对象复制")
    @Test
    public void testCopyProperties() throws InvocationTargetException, IllegalAccessException {
        users.setUsername("user-name");
        users.setPassword("user-pwd");
        // 将后面的复制到前面
        BeanUtils.copyProperties(empolyee, users);

        System.out.println("users = " + users.toString());
        System.out.println("empolyee = " + empolyee.toString());
        users.setUsername("user-name-modify");
        users.setPassword("user-pwd-modify");
        System.out.println("users = " + users.toString());
        System.out.println("empolyee = " + empolyee.toString());


    }
}
