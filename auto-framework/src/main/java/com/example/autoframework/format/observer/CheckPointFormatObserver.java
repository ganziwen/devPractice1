package com.example.autoframework.format.observer;

import com.example.autoframework.annotation.CheckPoint;
import com.example.autoframework.exception.IllegalFormatException;
import com.example.autoframework.format.FormatObserver;
import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CheckPointsFormatObserver
 * @Description
 * @date 2021/11/13 11:22
 */
public class CheckPointFormatObserver implements FormatObserver {
    @Override
    public void format(Method method) {
        // 单个标签的可以这样判断
        // boolean annotationPresent = method.isAnnotationPresent(CheckPoint.class);
        // if (!annotationPresent) {
        //     throw new IllegalFormatException("@checkPint should be set.eg:@CheckPoint(\"检查点\")");
        // }

        // 反射
        CheckPoint[] checkPoints = method.getAnnotationsByType(CheckPoint.class);

        if (checkPoints.length == 0) {
            throw new IllegalFormatException("@checkPint should be set.eg:@CheckPoint(\"检查点\")");
        }

        for (CheckPoint checkPoint : checkPoints) {
            RequiredUtils.requireNotNullOrEmpty(checkPoint.value().trim(), "@checkPint value should not be null or empty");
        }

    }
}
