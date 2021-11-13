package com.example.autoframework.util;

import com.example.autoframework.exception.RequiredException;
import org.assertj.core.util.Strings;

import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RequiredUtils
 * @Description
 * @date 2021/11/13 11:34
 */
public class RequiredUtils {

    private RequiredUtils() {

    }

    public static String requireNotNullOrEmpty(String str, String msg) {
        if (Strings.isNullOrEmpty(str)) {
            throw new RequiredException(msg);
        }
        return str;
    }

    public static Object requiredNotNull(Object obj, String msg) {
        if (Objects.isNull(obj)) {
            throw new RequiredException(msg);
        }
        return obj;
    }

}
