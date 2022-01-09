package com.development.mock.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RandomUtils
 * @Description
 * @date 2022/1/9 15:39
 */
public class RandomUtils {
    public static String random32Id(int size) {
        if (size <= 0) {
            throw new IllegalStateException("size should not less than 0");
        } else {
            return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        }

    }
}
