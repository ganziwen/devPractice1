package com.example.autoframework.extention.format;

import com.example.autoframework.exception.IllegalFormatException;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FormatObserver
 * @Description
 * @date 2021/11/13 11:13
 */
public interface FormatObserver {
    public void format(Method method) throws IllegalFormatException;
}
