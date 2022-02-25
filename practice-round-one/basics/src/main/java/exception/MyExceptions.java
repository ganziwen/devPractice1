package exception;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MyExceptions
 * @Description 自动以异常
 * @date 2022/2/25 22:15
 */
public class MyExceptions extends Exception {
    public MyExceptions() {
    }

    public MyExceptions(String message) {
        super(message);
    }

    public MyExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public MyExceptions(Throwable cause) {
        super(cause);
    }

    public MyExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
