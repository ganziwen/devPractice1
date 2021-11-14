package com.example.autoframework.extention.format;

import com.example.autoframework.annotation.CaseGroup;
import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseGroupFormatObserver
 * @Description
 * @date 2021/11/13 11:22
 */
public class CaseGroupFormatObserver implements FormatObserver {
    @Override
    public void format(Method method) {
        // 反射
        boolean annotationPresent = method.isAnnotationPresent(CaseGroup.class);
        if (annotationPresent) {
            CaseGroup annotation = method.getAnnotation(CaseGroup.class);
            RequiredUtils.requireNotNullOrEmpty(annotation.group().trim(), "@CaseGroup 'group' should not be null or empty");
            RequiredUtils.requireNotNullOrEmpty(annotation.team().trim(), "@CaseGroup 'team' should not be null or empty");

        }
    }
}
