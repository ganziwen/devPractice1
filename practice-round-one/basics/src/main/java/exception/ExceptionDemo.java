package exception;

import org.testng.annotations.Test;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ExceptionDemo
 * @Description
 * @date 2022/2/25 21:42
 */
public class ExceptionDemo {

    /**
     * 受检异常。就是程序判断有可能会出现的异常，idea会提示的
     *
     * @throws IOException
     */

    public void exceptionCheck() throws IOException {
        System.out.println("ExceptionDemo.testExceptionCheck");
    }


    public void exceptionNotCheck() throws NullPointerException {
        System.out.println("ExceptionDemo.testExceptionNotCheck");
    }

    @Test
    public void testExceptionCheck() {

        exceptionNotCheck();

        // 受检异常异常必须捕获或者上抛
        try {
            exceptionCheck();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("this is finally");
        }
    }

    @Test
    public void testWithAutoClose() {
        try (InnerInterface innerInterface = new InnerInterface()) {
            try {
                innerInterface.print(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMyRuntimeException() {
        try (InnerInterface innerInterface = new InnerInterface()) {
            innerInterface.print(0);
        } catch (Exception e) {
            throw new MyRuntimeException(e, 0);
        } finally {
            System.out.println("finally");
        }
    }

}


class InnerInterface implements Closeable {

    public void print(int n) throws Exception {

        if (n == 0) {
            throw new Exception("输入为 0");
        }
        System.out.println("InnerInterface.print");
    }

    @Override
    public void close() throws IOException {
        System.out.println("InnerInterface.close");
    }
}