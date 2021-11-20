package com.example.autoframework.extention.engine;

import com.example.autoframework.annotation.CaseGroup;
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
 * @ClassName CaseDiscoveryFIlter
 * @Description
 * @date 2021/11/20 16:03
 */
public class CaseDiscoveryFilter implements PostDiscoveryFilter {
    private CaseSelector caseSelector;

    public CaseDiscoveryFilter(CaseSelector caseSelector) {
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
            CaseGroup[] caseGroups = testMethod.getAnnotationsByType(CaseGroup.class);
            // if (caseGroups.length > 0) {
            //     CaseGroup onegroup = caseGroups[0];
            // } else {
            //     CaseGroup onegroup = null;
            // }
            long selectTagAndGroup = Arrays.stream(caseTags).filter(caseTag ->
                    caseTag.key().equals(caseSelector.key()) && caseTag.val().equals(caseSelector.val())
            ).count();

            // tag 是 0-1 个
            long selectTagCount = Arrays.stream(caseTags).filter(caseTag ->
                    caseTag.key().equals(caseSelector.key()) && caseTag.val().equals(caseSelector.val())
            ).count();

            // team 和 group 是 0-1 个
            long caseGroupsCount = Arrays.stream(caseGroups).filter(caseGroup ->
                    caseGroup.team().equals(caseSelector.team()) && caseGroup.group().equals(caseSelector.group())
            ).count();

            switch (caseTags.length + caseGroups.length) {
                case 0:
                    return FilterResult.includedIf(false);
                case 1:
                    if (caseTags.length == selectTagCount && caseGroups.length == caseGroupsCount) {
                        return FilterResult.includedIf(true);
                    }
                case 2:
                    if (caseTags.length == selectTagCount && caseGroups.length == caseGroupsCount) {
                        return FilterResult.includedIf(true);
                    }
                default:
                    break;
            }

            // // 没有 key 以及 team
            // if ((caseTags.length + caseGroups.length) == 0) {
            //     return FilterResult.includedIf(false);
            //
            // } else if ((caseTags.length + caseGroups.length) == 0) {
            // }
            //
            // if (selectTagCount > 0 || caseGroupsCount > 0) {
            //     // 命中 tag 规则之后，就 include 进去
            //     return FilterResult.includedIf(true);
            // }
        }
        return FilterResult.includedIf(false);
    }
}
