package singleton;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SingletonInnerClass
 * @Description
 * @date 2021/7/25 16:55
 * 比较推荐的：匿名内部类的方式
 */
public final class SingletonInnerClass {
    private SingletonInnerClass() {
    }

    /**
     * 虚拟机帮我们保证的单例
     */
    private static class ClassHolder {
        private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
    }

    /**
     * 不调用 of() 方法,就不会去实例化本身这个对象
     *
     * @return
     */
    public static SingletonInnerClass of() {
        return ClassHolder.INSTANCE;
    }
}
