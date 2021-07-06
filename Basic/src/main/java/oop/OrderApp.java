package oop;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-10:09
 */
public class OrderApp {
    public static void main(String[] args) {
        // 调用无参构造器
        new Order();
        // 调用有参构造器
        new Order(2014L, "1234", "abcd");
    }
}
