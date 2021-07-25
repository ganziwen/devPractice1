package singleton;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ThreadSingleton
 * @Description
 * @date 2021/7/25 17:30
 */
public class ThreadSingleton {


    private ExecutorService executorService;

    private ThreadSingleton() {
        // 获取当前核心数 *2,有的可以 *3 或者 4 都行,看业务需求
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        // 打印 service 的 hashCode 值
        System.out.println("executorService.hashCode() = " + executorService.hashCode());
    }

    private static class ClassHolder {
        private static final ThreadSingleton holder = new ThreadSingleton();
    }

    public static ThreadSingleton of() {
        return ClassHolder.holder;
    }

    /**
     * 单个任务
     *
     * @param runnable
     */
    public void submit(Runnable runnable) {
        this.executorService.submit(runnable);
    }

    /**
     * 任务列表
     *
     * @param runnable
     */
    public void submit(List<Runnable> runnable) {
        for (Runnable runnable1 : runnable) {

            this.executorService.submit(runnable1);
        }
    }


    /**
     * 不规范写法:创建一个线程池去执行任务.缺点在于,假设每次我们去调用 test 的方法，都会去启 32 个线程组成一个线程池;而且方法栈结束之后,线程池会被回收
     */
    public static void testUnNromal() {
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        executorService.submit(() -> System.out.println("ThreadSingleton.testUnNromal"));
    }
}

