package generic;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-18:30
 */
public class StaticTDemo {

    public static Object returndata(Object data) {
        System.out.println("data = " + data);
        return data;
    }

    /**
     * 针对方法使用泛型
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> T getData(T data) {
        System.out.println(data);
        return data;
    }
}
