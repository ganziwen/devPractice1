package com.example.autoframework.extention.engine;

import com.example.autoframework.annotation.CaseGroup;
import com.example.autoframework.annotation.CaseSelector;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.commons.util.StringUtils;
import org.junit.platform.engine.FilterResult;

import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseGroupDiscoveryFilter
 * @Description
 * @date 2021/11/14 18:00
 */
public class CaseGroupDiscoveryFilter extends AbstractDiscoveryFilter {
    public CaseGroupDiscoveryFilter(CaseSelector selector) {
        super(selector);
    }

    @Override
    protected boolean preFilter(CaseSelector selector) {
        return StringUtils.isNotBlank(selector.team().trim()) && StringUtils.isNotBlank(selector.group().trim());
    }

    @Override
    protected FilterResult onApply(TestMethodTestDescriptor descriptor) {
        Method testMethod = descriptor.getTestMethod();
        boolean isCaseGroupSet = testMethod.isAnnotationPresent(CaseGroup.class);
        // 没设置 group,则不将该方法纳入 case 内
        if (!isCaseGroupSet) {
            return FilterResult.includedIf(false);
        }
        // 设置了 group
        CaseGroup caseGroup = testMethod.getAnnotation(CaseGroup.class);
        if (selector.team().trim().equals(caseGroup.team().trim()) && selector.group().trim().equals(caseGroup.group().trim())) {
            return FilterResult.includedIf(true);
        }
        return FilterResult.includedIf(false);

    }

}
// private CaseSelector caseSelector;
//
// public CaseGroupDiscoveryFilter(CaseSelector caseSelector) {
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
//         if (!testMethod.isAnnotationPresent(CaseGroup.class)) {
//             return FilterResult.includedIf(true);
//
//         } else {
//             CaseGroup[] caseGroups = testMethod.getAnnotationsByType(CaseGroup.class);
//             long selectTagCount = Arrays.stream(caseGroups).filter(group ->
//                     group.team().equals(caseSelector.team()) && group.group().equals(caseSelector.group())
//             ).count();
//             if (selectTagCount > 0) {
//                 // 命中 tag 规则之后，就 include 进去
//                 return FilterResult.includedIf(true);
//             }
//         }
//
//     }
//     return FilterResult.includedIf(false);
// }

