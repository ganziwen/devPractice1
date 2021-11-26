package com.example.autoframework.engine.filter;

import com.example.autoframework.annotation.CaseGroup;
import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.annotation.CaseTag;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.commons.util.StringUtils;
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

            // 单个用例上的 tag ,可能会有多个,命中的可能也是多个(但是注解上只有一个,所以这里可以先绕过)
            CaseTag[] caseTags = testMethod.getAnnotationsByType(CaseTag.class);
            // 单个用例上的 group ,数量是 0-1 个
            CaseGroup[] caseGroups = testMethod.getAnnotationsByType(CaseGroup.class);


            // 命中的 tag 是 0-1 个
            long selectTagCount = Arrays.stream(caseTags).filter(caseTag ->
                    caseTag.key().equals(caseSelector.key()) && caseTag.val().equals(caseSelector.val())
            ).count();

            // 命中的 team 和 group 是 0-1 个
            long caseGroupsCount = Arrays.stream(caseGroups).filter(caseGroup ->
                    caseGroup.team().equals(caseSelector.team()) && caseGroup.group().equals(caseSelector.group())
            ).count();

            // 根据标签是否空白区反选匹配到的 count 数量,这部分比较冗余看看怎么优化一下
            if (StringUtils.isNotBlank(caseSelector.key()) && StringUtils.isNotBlank(caseSelector.team())) {
                if (selectTagCount == 1 && caseGroupsCount == 1) {
                    return FilterResult.includedIf(true);
                } else {
                    return FilterResult.includedIf(false);
                }
            } else if (StringUtils.isBlank(caseSelector.key()) && StringUtils.isNotBlank(caseSelector.team())) {
                if (caseGroupsCount == 1) {
                    return FilterResult.includedIf(true);
                } else {
                    return FilterResult.includedIf(false);
                }
            } else if (StringUtils.isNotBlank(caseSelector.key()) && StringUtils.isBlank(caseSelector.team())) {
                if (selectTagCount == 1) {
                    return FilterResult.includedIf(true);
                } else {
                    return FilterResult.includedIf(false);
                }
            } else {
                return FilterResult.includedIf(false);
            }
        }
        return FilterResult.includedIf(false);
    }
}
