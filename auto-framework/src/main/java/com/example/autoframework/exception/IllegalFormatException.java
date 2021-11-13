package com.example.autoframework.exception;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName IllegalFormatException
 * @Description
 * @date 2021/11/13 11:44
 */
public class IllegalFormatException extends RuntimeException {

    public IllegalFormatException() {
        super("IllegalFormatException");
    }

    public IllegalFormatException(String msg) {
        super(msg);
    }
}
