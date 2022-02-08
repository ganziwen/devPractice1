package object_oriented;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/2/8-18:18
 * 面向对象
 */
public class Basic {

    @Test
    public void testCreateOneObject() {
        Students students = new Students();
        System.out.println(students.getNameAndAge(18));
        System.out.println(students.getNameAndAge("方法重载第一种"));
        System.out.println(students.getNameAndAge("方法重载第二种", 19));
        System.out.println(students.joinAllArguments("哈哈", "这是啥"));
    }
}

class Students {
    int age;
    String name;

    public String getNameAndAge(int age) {
        return String.format("age is %s", age);
    }

    public String getNameAndAge(String name) {
        return String.format("name is:%s", name);
    }

    /**
     * 方法的重载,参数列表不一致，返回值不一定要一致
     *
     * @param name
     * @param age
     * @return
     */
    public String getNameAndAge(String name, int age) {
        return String.format("name is:%s,age is %s", name, age);
    }

    /**
     * 可变参数
     *
     * @param strings
     * @return
     */
    public String joinAllArguments(@NotNull String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }
}
