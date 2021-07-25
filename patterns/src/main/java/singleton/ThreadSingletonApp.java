package singleton;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ThreadSingletonApp
 * @Description
 * @date 2021/7/25 17:41
 */
public class ThreadSingletonApp {
    public static void main(String[] args) {
        // 打印结果可以看到 service 只打印了一次,说明线性池只实例化出来一次
        testNormal();
    }


    public static void testNormal() {
        ThreadSingleton.of().submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadSingletonApp.testNormal");
            }
        });

        ThreadSingleton.of().submit(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadSingletonApp.testNormal");
            }
        });
    }
}
