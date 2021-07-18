package anonymous;

import com.mysql.cj.util.TestUtils;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FileApp
 * @Description
 * @date 2021/7/18 21:52
 * <p>
 * 匿名内部类
 */


public class FileApp {
    public static void main(String[] args) {
        testNormal();
        testAnon();
    }

    interface FileInter {
        void reader();

        void reader2();
    }

    /**
     * 不利用匿名内部类的的方式,需要用抽象类做中间载体
     */
    public static void testNormal() {
        FileApp2 fileApp2 = new FileApp2();
        fileApp2.reader();
    }

    public static void testAnon() {
        new FileInter() {
            @Override
            public void reader() {
                System.out.println("FileApp.reader");
            }

            @Override
            public void reader2() {
                System.out.println("FileApp.reader2");
            }
        }.reader();

    }

    /**
     * 抽象类
     */
    static class FileApp2 implements FileInter {
        @Override
        public void reader() {
            System.out.println("FileApp2.reader");
        }

        @Override
        public void reader2() {

        }
    }


}


