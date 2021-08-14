package singleton;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SingletonApp
 * @Description 单例设计模式
 * @date 2021/7/25 16:38
 * 优缺点:
 * - 懒汉式:占用资源,一直会被实例化
 * - 饿汉式:有线程安全问题,需要自行解决
 * - 匿名内部类:推荐写法,需要用的时候再去实例化,不会有资源浪费;也不会有线程安全问题
 * - 枚举类；推荐法,但是比起匿名内部类优先级不是很高,实质上也是懒汉式
 */
public class SingletonApp {
    public static void main(String[] args) {
        testSingleHungry();
        testSingletonLazy();
        testSingletonInnerClass();
        testSingletonUseEnum();
    }

    /**
     * 饿汉式
     */
    public static void testSingleHungry() {
        System.out.println("饿汉式");
        SingletonHungry instance1 = SingletonHungry.getInstance();
        SingletonHungry instance2 = SingletonHungry.getInstance();
        SingletonHungry instance3 = SingletonHungry.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        System.out.println("instance3 = " + instance3);

    }

    /**
     * 懒汉式
     */
    public static void testSingletonLazy() {
        System.out.println("懒汉式");

        SingletonLazy instance1 = SingletonLazy.getInstance();
        SingletonLazy instance2 = SingletonLazy.getInstance();
        SingletonLazy instance3 = SingletonLazy.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        System.out.println("instance3 = " + instance3);

    }

    /**
     * 匿名内部类方式
     */
    public static void testSingletonInnerClass() {
        System.out.println("匿名内部类");

        SingletonInnerClass instance1 = SingletonInnerClass.of();
        SingletonInnerClass instance2 = SingletonInnerClass.of();
        SingletonInnerClass instance3 = SingletonInnerClass.of();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        System.out.println("instance3 = " + instance3);
    }

    /**
     * 利用枚举类保证的全局唯一
     */
    public static void testSingletonUseEnum() {
        System.out.println("枚举类");
        SingletonUseEnum instance1 = SingletonUseEnum.INSTANCE.test();
        SingletonUseEnum instance2 = SingletonUseEnum.INSTANCE.test();
        SingletonUseEnum instance3 = SingletonUseEnum.INSTANCE.test();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);
        System.out.println("instance3 = " + instance3);
    }

}
