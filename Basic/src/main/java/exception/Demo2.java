package exception;

import io.BufferReaderAndPrintWriter;

import java.io.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo2
 * @Description
 * @date 2021/7/11 14:37
 */
public class Demo2 {

    public static void main(String[] args) {

        // foo1();
        foo2();
    }

    /**
     * jdk 1.7 之前的写法
     */
    public static void foo1() {
        String filePath = "Basic/src/main/java/io/testReadFile.txt";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(filePath)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // 只是打印提示信息
            System.out.println(e.getMessage());
            System.out.println("-------");
            // 打印整个异常的堆栈信息
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader == null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * jdk 1.7 新特性的写法
     */
    public static void foo2() {
        String filePath = "Basic/src/main/java/io/testReadFile.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // 只是打印提示信息
            System.out.println(e.getMessage());
            System.out.println("-------");
            // 打印整个异常的堆栈信息
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实例化的对象必须实现自 Closeable 接口
     */
    public static void foo3() {
        try (Foo1 foo1 = new Foo1()) {

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 报错
        // try (Foo2 foo1 = new Foo2()) {
        //
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    static class Foo1 implements Closeable {
        @Override
        public void close() throws IOException {

        }
    }

    class Foo2 {

    }
}



