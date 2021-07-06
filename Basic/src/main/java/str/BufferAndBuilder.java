package str;

/**
 * StringBuffer 和 StringBuilder
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-16:02
 */
public class BufferAndBuilder {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1() {
        String string = "a,b,c,d,e,f,g";
        String[] array = string.split(",");
        String res = "";
        for (String str : array) {
            // 字符串循环拼接;这里导致的问题是会一直创建 str 对象,堆内存可能会溢出
            res += str + "*";
        }
        System.out.println("res = " + res);
    }

    /**
     * StringBuilder用来拼接字符串
     */
    public static void test2() {
        String string = "a,b,c,d,e,f,g";
        String[] array = string.split(",");
        StringBuilder res = new StringBuilder();
        for (String str : array) {
            res.append(str).append("*");
        }
        System.out.println("res = " + res);
    }

    public static void test3() {
        String string = "a,b,c,d,e,f,g";
        String[] array = string.split(",");
        // 线程安全的:StringBuffer
        StringBuffer res = new StringBuffer();
        for (String str : array) {
            res.append(str).append("*");
        }
        System.out.println("res = " + res);
    }
}
