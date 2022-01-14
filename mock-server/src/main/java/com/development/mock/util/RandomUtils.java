package com.development.mock.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
    /**
     * 产生 32 位随机数:时间拼接_8位_8位
     *
     * @param size
     * @return
     */
    public static String random32Id() {

        String time14Stamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        return time14Stamp + "_" + RandomStringUtils.randomAlphanumeric(8) + "_" + RandomStringUtils.randomAlphanumeric(8);
    }

    public static String randomId(int size) {

        return RandomStringUtils.randomAlphanumeric(size);
    }

    @Test
    public void testRandom32Id() {
        String random32Id = random32Id();
        System.out.println("random32Id = " + random32Id);
    }

    @Test
    public void testRandomId() {
        int size = 6;
        String randomId = randomId(size);
        System.out.println("randomId = " + randomId);
    }


}


