package com.example.autoframework.extention.format;

import com.example.autoframework.annotation.CaseTag;
import com.example.autoframework.exception.IllegalFormatException;
import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseTagFormatObserver
 * @Description
 * @date 2021/11/13 11:21
 */
public class CaseTagFormatObserver implements FormatObserver {
    @Override
    public void format(Method method) {
        // 单个标签可以这样判断
        // boolean annotationPresent = method.isAnnotationPresent(CaseTag.class);
        // if (!annotationPresent) {
        //     throw new IllegalFormatException("@CaseTag should be set.eg:@CaseTag(key = \"model\", val = \"pay\")");
        // }

        // 反射
        CaseTag[] tags = method.getAnnotationsByType(CaseTag.class);
        if (tags.length == 0) {
            throw new IllegalFormatException("@CaseTag should be set.eg:@CaseTag(key = \"model\", val = \"pay\")");
        }

        for (CaseTag tag : tags) {
            RequiredUtils.requireNotNullOrEmpty(tag.key().trim(), "@CaseTag key should not be null or empty");
            RequiredUtils.requireNotNullOrEmpty(tag.val().trim(), "@CaseTag val should not be null or empty");
        }
    }
}
