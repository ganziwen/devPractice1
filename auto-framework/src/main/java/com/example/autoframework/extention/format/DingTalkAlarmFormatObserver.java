package com.example.autoframework.extention.format;

import com.example.autoframework.annotation.DingTalkAlarm;
import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DingTalkAlarmFormatObserver
 * @Description
 * @date 2021/11/13 11:22
 */
public class DingTalkAlarmFormatObserver implements FormatObserver {
    @Override
    public void format(Method method) {
        // 反射
        boolean annotationPresent = method.isAnnotationPresent(DingTalkAlarm.class);
        if (annotationPresent) {
            DingTalkAlarm annotation = method.getAnnotation(DingTalkAlarm.class);
            RequiredUtils.requireNotNullOrEmpty(annotation.token().trim(), "@DingTalkAlarm value should not be null or empty");

        }
    }
}
