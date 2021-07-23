package thread;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/23-14:26
 * 线程安全
 */
public class ThreadSecurity {
    public static void main(String[] args) {
        Table table = new Table();
        Table.Person p1 = table.new Person();
        Table.Person p2 = table.new Person();
        p1.start();
        p2.start();
    }
}

class Table {
    // 总共 5 个豆子
    int beanCount = 5;

    /**
     * 取豆子的方法
     *
     * @return
     */
    public int getBeans() {
        if (beanCount == 0) {
            throw new RuntimeException("豆子没了");
        }
        try {

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return beanCount--;
    }

    /**
     * 人去取豆子
     */
    class Person extends Thread {
        @Override
        public void run() {
            while (true) {
                int beans = getBeans();
                System.out.println(this.getName() + " 剩下豆子数:" + beans);
            }
        }
    }

}
