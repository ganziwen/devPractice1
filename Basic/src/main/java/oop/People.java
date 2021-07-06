package oop;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-10:25
 */
public class People {
    private String name;
    private static String info;

    public void foo1() {
        System.out.println("People.foo1");
    }

    // People 类存储在方法区内,即可进行调用;而不用到下一步(下一步就是类的实例化)
    public static void foo2() {
        System.out.println("People.foo2");
    }
}
