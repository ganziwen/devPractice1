package com.example.autoframework.extention.engine;

import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.annotation.CaseTag;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseTagDiscoveryFilter
 * @Description 筛选 caseTag
 * @date 2021/11/14 16:42
 */
public class CaseTagDiscoveryFilter implements PostDiscoveryFilter {


    private CaseSelector caseSelector;

    public CaseTagDiscoveryFilter(CaseSelector caseSelector) {
        this.caseSelector = caseSelector;
    }

    /**
     * 筛选 caseTag
     *
     * @param testDescriptor
     * @return
     */
    @Override
    public FilterResult apply(TestDescriptor testDescriptor) {

        if (testDescriptor instanceof TestMethodTestDescriptor) {
            TestMethodTestDescriptor descriptor = (TestMethodTestDescriptor) testDescriptor;
            Method testMethod = descriptor.getTestMethod();
            CaseTag[] caseTags = testMethod.getAnnotationsByType(CaseTag.class);
            long selectTagCount = Arrays.stream(caseTags).filter(tag ->
                    tag.key().equals(caseSelector.key()) && tag.val().equals(caseSelector.val())
            ).count();
            if (selectTagCount > 0) {
                return FilterResult.includedIf(true);
            }
        }
        return FilterResult.includedIf(false);
    }
}
