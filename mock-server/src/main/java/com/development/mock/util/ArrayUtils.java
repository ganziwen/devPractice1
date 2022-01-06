package com.development.mock.util;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/6-11:33
 */
public final class ArrayUtils {

    public static String getFirstValue(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";

        }
        return strArr[0];
    }
}
