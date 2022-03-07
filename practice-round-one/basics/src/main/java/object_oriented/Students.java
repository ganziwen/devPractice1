package object_oriented;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/1-16:49
 */
public class Students {
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
    public String joinAllArguments( String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }
}
