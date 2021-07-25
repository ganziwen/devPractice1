package singleton;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SingletonDemo1
 * @Description
 * @date 2021/7/25 16:37
 * 饿汉式单例
 * 缺点:程序没有用到的时候,也会实例化出来一个对象,如果是对资源开销比较大的话,得不偿失
 */
public final class SingletonHungry {
    private SingletonHungry() {

    }

    // 这里不管需不需要用到这个单例,就一定会去实例化出来一个对象
    private static final SingletonHungry INSTANCE = new SingletonHungry();

    /**
     * 暴露给外部的就是个实例
     *
     * @return
     */
    public static SingletonHungry getInstance() {
        return INSTANCE;
    }
}
