package str;

import java.util.Locale;

/**
 * String 的常用方法
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-15:00
 */
public class StringFunc {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        String string = "hello world";
        // 获取字符串长度
        int length = string.length();

        // 判断值是否一致
        boolean bool = "helloworld".equals(string);

        // 根据下标获取字符
        char chars = string.charAt(0);
        System.out.println("chars = " + chars);

        // 判断是否包含某个字符串
        boolean bool2 = string.contains("hello");

        // 判断字符串前缀
        boolean bool3 = string.startsWith("h");

        // 判断字符串后缀
        boolean bool4 = string.startsWith("d");

        // 判断字符串是否为空,空字符串是"",而不是 null
        boolean bool5 = string.isEmpty();

        // 忽略大小写判断
        boolean bool6 = string.equalsIgnoreCase("HELLOWORLD");

        // 替换其中的某些字符串
        String string2 = string.replace("e", "E");
        String string3 = string.replaceAll("e", "EEE");
        String string4 = string.replaceFirst("e", "EEE");

        // 按""分割成一个 String 数组
        String[] string5 = string.split("");

        // 截取 1-5 的位置(左闭右开区间)
        String string6 = string.substring(1, 5);

        // String 的静态方法
        String stringDormate = "第一个参数是%s，第二个参数是%s";
        String string7 = String.format(string, stringDormate);

        // 字符串转小写,转大写
        String string8 = string.toLowerCase();
        String string9 = string.toUpperCase();


    }

}
