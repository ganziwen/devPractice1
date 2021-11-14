package com.example.autoframework.exception;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RequireException
 * @Description
 * @date 2021/11/13 11:41
 */
public class RequiredException extends BaseException {


    public RequiredException() {
        super("RequireException");
    }

    public RequiredException(String msg) {
        super(msg);
    }
}
