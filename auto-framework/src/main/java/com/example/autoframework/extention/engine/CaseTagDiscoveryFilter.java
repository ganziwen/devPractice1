package com.example.autoframework.extention.engine;

import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.annotation.CaseTag;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.commons.util.StringUtils;
import org.junit.platform.engine.FilterResult;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseTagDiscoveryFilter
 * @Description 筛选 caseTag
 * @date 2021/11/14 16:42
 */
public class CaseTagDiscoveryFilter extends AbstractDiscoveryFilter {

    public CaseTagDiscoveryFilter(CaseSelector selector) {
        super(selector);
    }

    @Override
    protected boolean preFilter(CaseSelector selector) {

        return StringUtils.isNotBlank(selector.key().trim()) && StringUtils.isNotBlank(selector.val().trim());

    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor descriptor) {
        Method testMethod = descriptor.getTestMethod();
        CaseTag[] caseTags = testMethod.getAnnotationsByType(CaseTag.class);
        long selectTagCount = Arrays.stream(caseTags).filter(tag ->
                tag.key().trim().equals(selector.key().trim()) && tag.val().trim().equals(selector.val().trim())
        ).count();
        System.out.println("selectTagCount = " + selectTagCount);

        return selectTagCount > 0 ? FilterResult.includedIf(true) : FilterResult.includedIf(false);
    }

    // private CaseSelector caseSelector;
    //
    // public CaseTagDiscoveryFilter(CaseSelector caseSelector) {
    //     this.caseSelector = caseSelector;
    // }
    //
    // /**
    //  * 筛选 caseTag
    //  *
    //  * @param testDescriptor
    //  * @return
    //  */
    // @Override
    // public FilterResult apply(TestDescriptor testDescriptor) {
    //
    //     if (testDescriptor instanceof TestMethodTestDescriptor) {
    //         TestMethodTestDescriptor descriptor = (TestMethodTestDescriptor) testDescriptor;
    //         Method testMethod = descriptor.getTestMethod();
    //         CaseTag[] caseTags = testMethod.getAnnotationsByType(CaseTag.class);
    //         long selectTagCount = Arrays.stream(caseTags).filter(tag ->
    //                 tag.key().equals(caseSelector.key()) && tag.val().equals(caseSelector.val())
    //         ).count();
    //         if (selectTagCount > 0) {
    //             // 命中 tag 规则之后，就 include 进去
    //             return FilterResult.includedIf(true);
    //         }
    //     }
    //     return FilterResult.includedIf(false);
    // }
}
