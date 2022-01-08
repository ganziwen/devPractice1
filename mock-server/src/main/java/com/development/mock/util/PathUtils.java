package com.development.mock.util;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PathUtils
 * @Description
 * @date 2022/1/8 20:05
 */
public final class PathUtils {

    public static String joinPath(String parent, String current) {
        return parent + "/" + current;
    }
}
