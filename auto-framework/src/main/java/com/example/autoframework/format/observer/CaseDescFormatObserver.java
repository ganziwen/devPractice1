package com.example.autoframework.format.observer;

import com.example.autoframework.annotation.CaseDesc;
import com.example.autoframework.exception.IllegalFormatException;
import com.example.autoframework.format.FormatObserver;
import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseDescFormatObserver
 * @Description
 * @date 2021/11/13 11:22
 */
public class CaseDescFormatObserver implements FormatObserver {
    @Override
    public void format(Method method) {
        // 反射
        boolean annotationPresent = method.isAnnotationPresent(CaseDesc.class);
        if (!annotationPresent) {
            throw new IllegalFormatException("@CaseDesc should be set.eg:@CaseDesc(desc = \"需求描述\", owner = \"用例作者\")");
        }
        CaseDesc[] caseDescs = method.getAnnotationsByType(CaseDesc.class);
        for (CaseDesc caseDesc : caseDescs) {
            RequiredUtils.requireNotNullOrEmpty(caseDesc.desc().trim(), "@CaseDesc desc should not be null or empty");
            RequiredUtils.requireNotNullOrEmpty(caseDesc.owner().trim(), "@CaseDesc owner should not be null or empty");
        }

    }
}
