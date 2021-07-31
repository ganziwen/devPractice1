package course.aop.aop;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FooServiceNoInter
 * @Description
 * @date 2021/8/1 0:22
 * 其他 jar 的类照样可以截取打印
 */
public class Foo2ServiceOtherJar {
    public void foo2() {
        System.out.println("Foo2ServiceOtherJar.foo2");
    }
}
