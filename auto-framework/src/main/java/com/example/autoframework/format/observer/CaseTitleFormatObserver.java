package com.example.autoframework.format.observer;

import com.example.autoframework.annotation.CaseTitle;
import com.example.autoframework.exception.IllegalFormatException;
import com.example.autoframework.format.FormatObserver;
import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseTitleFormatObserver
 * @Description
 * @date 2021/11/13 11:22
 */
public class CaseTitleFormatObserver implements FormatObserver {
    @Override
    public void format(Method method) {
        // 反射
        boolean annotationPresent = method.isAnnotationPresent(CaseTitle.class);
        if (!annotationPresent) {
            throw new IllegalFormatException("@CaseTitle should be set.eg:@CaseTitle(\"用例标题\")");
        }
        CaseTitle[] caseTitles = method.getAnnotationsByType(CaseTitle.class);

        for (CaseTitle caseTitle : caseTitles) {
            RequiredUtils.requireNotNullOrEmpty(caseTitle.value().trim(), "@CaseTitle value should not be null or empty");
        }
    }
}
