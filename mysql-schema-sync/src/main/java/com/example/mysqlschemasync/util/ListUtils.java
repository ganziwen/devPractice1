package com.example.mysqlschemasync.util;

import java.util.List;
import java.util.Objects;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/30-10:41
 * 用来判断 list 以及元素是否为空的工具类
 */
public class ListUtils {
    public static <T> boolean isContains(List<T> list, T t) {
        if (Objects.isNull(list) || Objects.isNull(t)) {
            return false;
        }
        return list.contains(t);
    }
}
