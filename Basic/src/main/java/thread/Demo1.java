package thread;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/22-20:01
 */
public class Demo1 {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        testInterrupt();
    }

    public static void test1() {
        MyThread myThread = new MyThread();
        myThread.start();

    }

    /**
     * 比较推荐的方式,thread 和任务分离
     */
    public static void test2() {
        Aoo aoo = new Aoo();
        Boo boo = new Boo();
        Thread thread = new Thread(aoo);
        Thread thread1 = new Thread(boo);
        thread.start();
        thread1.start();
    }

    /**
     * 匿名内部类,lambda 表达式的方式,少了 implate
     */
    public static void test3() {
        Thread thread = new Thread(() -> System.out.println("Demo1.run"));
        thread.start();
    }

    /**
     * 唤醒中断
     */
    public static void testInterrupt() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    System.out.println("没啥事,接着锤");
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        System.out.println("干嘛呢,破了相了");
                        break;
                    }
                }
            }
        };

        thread1.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("八十,八十");
                }
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("砸穿了");
                thread1.interrupt();
            }
        };

        thread2.start();

    }

}


/**
 * 继承 Thread ,线程和任务只能在一起
 */
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread.run");

    }
}

/**
 * 单独创建的任务1
 */
class Aoo implements Runnable {
    @Override
    public void run() {
        System.out.println("Aoo.run");
    }
}

/**
 * 单独创建的任务2
 */
class Boo implements Runnable {
    @Override
    public void run() {
        System.out.println("Boo.run");
    }
}

