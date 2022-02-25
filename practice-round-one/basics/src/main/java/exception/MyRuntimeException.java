package exception;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MyRuntimeException
 * @Description
 * @date 2022/2/25 22:19
 */
public class MyRuntimeException extends RuntimeException {
    int code;

    public MyRuntimeException(int code) {
        this.code = code;
    }

    public MyRuntimeException(String message, int code) {
        super(message);
        this.code = code;
    }

    public MyRuntimeException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public MyRuntimeException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }
}
