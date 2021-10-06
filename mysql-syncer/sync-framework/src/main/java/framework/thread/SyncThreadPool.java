package framework.thread;

import java.util.concurrent.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncThreadPool
 * @Description 线程池
 * @date 2021/10/6 13:44
 */
public class SyncThreadPool {

    private ExecutorService executorService;

    /**
     * 创建线程池
     */
    private SyncThreadPool() {
        this.executorService = Executors.newFixedThreadPool(64);

    }

    private static class ClassHolder {
        private static final SyncThreadPool HOLDER = new SyncThreadPool();
    }

    public static SyncThreadPool of() {
        return ClassHolder.HOLDER;
    }

    /**
     * 无返回
     *
     * @param runnable
     */
    public void run(Runnable runnable) {
        this.executorService.execute(runnable);

    }

    /**
     * 有返回
     *
     * @param callable
     * @param <T>
     * @return
     */
    public <T> Future<T> run(Callable<T> callable) {

        return this.executorService.submit(callable);

    }
}
