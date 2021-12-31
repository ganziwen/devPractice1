package oop;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/28-14:52
 */
public class StaticDemo2 {
    public static void main(String[] args) {
        Ioo i1 = new Joo();
        Ioo i2 = new Ioo();
        Joo j1 = new Joo();

        i1.g();// Joo.g
        i2.g();// Ioo.g
        new Joo().g();// Joo.g

        // 看数据的定义类型
        i1.f();// Ioo.g
        i2.f();// Ioo.g
        // 这个会自动推导出是 Joo 类型
        new Joo().f();// Joo.g
    }
}

class Ioo {

    public static void f() {
        System.out.println("Ioo.f");
    }

    public void g() {
        System.out.println("Ioo.g");
    }
}

class Joo extends Ioo {
    // 覆盖
    public static void f() {
        System.out.println("Joo.f");
    }

    // 重写
    @Override
    public void g() {
        System.out.println("Joo.g");
    }
}
