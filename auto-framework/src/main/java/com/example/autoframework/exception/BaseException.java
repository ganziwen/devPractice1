package com.example.autoframework.exception;

import java.util.Formatter;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName BaseException
 * @Description
 * @date 2021/11/13 12:11
 */
public class BaseException extends RuntimeException {
    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Object... args) {
        super(new Formatter().format(message, args).toString());
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
