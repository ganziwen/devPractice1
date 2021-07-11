package exception;


import java.io.IOException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo1
 * @Description
 * @date 2021/7/11 10:23
 */
public class Demo1 {
    public static void main(String[] args) {

        testRunTimeException();

        // 受检异常一定要捕获,或者向上传递
        try {
            testCheckedException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不受检异常
     *
     * @throws NullPointerException
     */
    public static void testRunTimeException() throws NullPointerException {
        System.out.println("Demo1.testRunTimeException");
    }

    /**
     * 受检异常
     *
     * @throws IOException
     */
    public static void testCheckedException() throws IOException {
        System.out.println("Demo1.testCheckedException");
    }
}
