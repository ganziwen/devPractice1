package thread;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/22-21:03
 */
public class ThreadFunc {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Coo());
        // 返回当前正在使用CPU资源的线程
        Thread currentThread = Thread.currentThread();

        // 给线程取名字
        thread.setName("新线程");

        // 进程的 id
        System.out.println("currentThread.getId() = " + thread.getId());
        // 进程名称
        System.out.println("currentThread.getName() = " + thread.getName());
        // 进程状态
        System.out.println("currentThread.getState() = " + thread.getState());
        System.out.println("currentThread.isAlive() = " + thread.isAlive());
        thread.start();
        System.out.println("currentThread.getState() = " + thread.getState());
        // 判断是否存活
        System.out.println("currentThread.isAlive() = " + thread.isAlive());


    }
}

/**
 * 设定的任务
 */
class Coo implements Runnable {
    @Override
    public void run() {
        System.out.println("Aoo.run");
    }
}