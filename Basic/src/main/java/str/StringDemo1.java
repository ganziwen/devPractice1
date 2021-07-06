package str;

/**
 * 字符串
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-14:31
 */
public class StringDemo1 {

    public static void main(String[] args) {

    }

    public static void test1() {
        // 最常用
        String string1 = "hello world";

        // String 是个类,其实也可以实例化
        // 特定场景采用 String 的构造器来构造一个变量
        String string2 = new String("hello world");

        byte[] buffer = new byte[128];
        // 特定场景采用 String 的构造器来构造一个变量
        String string3 = new String(buffer, 0, 128);
    }

    public static void test2() {
        String string1 = "hello world";
        String string2 = new String("hello world");
    }
}
