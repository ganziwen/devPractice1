package singleton;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SingletonLazy
 * @Description
 * @date 2021/7/25 16:49
 * 懒汉式
 */
public final class SingletonLazy {
    // 线程加锁要用 volatile 关键字
    private static volatile SingletonLazy INSTANCE;

    public SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        // 如果直接返回,会有线程的安全问题
        // return INSTANCE;

        // 优化写法:线程加锁
        if (INSTANCE == null) {
            synchronized (SingletonLazy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonLazy();
                }
            }
        }
        return INSTANCE;
    }
}
