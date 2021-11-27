package com.example.autoframework.util;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ReflectUtils
 * @Description 反射的工具类
 * @date 2021/11/27 16:25
 */
public class ReflectUtils {
    public static <T> T newInstance(Class<T> Clazz) {
        try {
            return Clazz.newInstance();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
