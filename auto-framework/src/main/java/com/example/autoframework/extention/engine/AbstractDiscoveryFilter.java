package com.example.autoframework.extention.engine;

import com.example.autoframework.annotation.CaseSelector;
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor;
import org.junit.platform.engine.FilterResult;
import org.junit.platform.engine.TestDescriptor;
import org.junit.platform.launcher.PostDiscoveryFilter;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AbstractDiscoveryFilter
 * @Description
 * @date 2021/11/14 18:20
 */
public abstract class AbstractDiscoveryFilter implements PostDiscoveryFilter {
    protected CaseSelector selector;


    public AbstractDiscoveryFilter(CaseSelector selector) {
        this.selector = selector;
    }

    protected abstract boolean preFilter(CaseSelector selector);

    protected abstract FilterResult onApply(TestMethodTestDescriptor descriptor);

    @Override
    public FilterResult apply(TestDescriptor testDescriptor) {
        if (!preFilter(this.selector)) {
            FilterResult.includedIf(true);
        }

        if (!(testDescriptor instanceof TestMethodTestDescriptor)) {
            return FilterResult.includedIf(false);

        }
        return onApply((TestMethodTestDescriptor) testDescriptor);
    }
}
