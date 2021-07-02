package oop;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/2-18:13
 */
public class Animal {
    //    无返回值;无参数
    void foo1() {

    }

    //    有返回值,返回值类型为 int ;无参数
    int foo2() {
        return 1;
    }

    //    有返回值,返回值类型为 int ;有参数,参数数据类型具体看列表内定义
    int foo2(int i1, String name) {
        return i1 + 1024;
    }

    //    ... 代表可变参,strs 是个数组,参数的个数不固定,相当于 Python 内的 *args
    void foo4(int i1, String... strs) {

    }

    void foo5() {
        foo4(1, "a");
        foo4(1, "a", "b");
        foo4(1, "a", "b", "c");
    }

}
