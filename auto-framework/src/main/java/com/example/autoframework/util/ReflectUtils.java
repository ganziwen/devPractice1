package com.example.autoframework.util;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ReflectUtils
 * @Description 反射的工具类
 * @date 2021/11/27 16:25
 */
public class ReflectUtils {
    /**
     * 反射的工具类方法，通过传入的 class ，返回其 instance
     */
    public static <T> T newInstance(Class<T> Clazz) {
        try {
            return Clazz.newInstance();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 通过类名以及方法名，反射拿到方法。如果方法名相同，但是参数列表不一样的话，用该方法就可能会有问题，其实还得加上参数列表，但是这里为了偷懒就省略了。其实还是可以使用 hutool 的工具类
     */
    public static Method getMethod(String className, String methodName) {
        RequiredUtils.requireNotNullOrEmpty(className, "className should not be empty!");
        RequiredUtils.requireNotNullOrEmpty(methodName, "methodName should not be empty!");

        try {
            return Class.forName(className).getMethod(methodName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

}
