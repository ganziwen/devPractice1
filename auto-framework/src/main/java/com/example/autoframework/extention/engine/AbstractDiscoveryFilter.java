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

    /**
     * 判断每一个测试方法是否有 tag 和 group 的标签,不为空返回为 true
     */
    protected abstract boolean preFilter(CaseSelector selector);

    /**
     * 直接处理,传进去的是每个用例的描述信息
     */
    protected abstract FilterResult onApply(TestMethodTestDescriptor descriptor);

    // 这里的逻辑需要着重理解一下,用来判断是否符合过滤条件的
    @Override
    public FilterResult apply(TestDescriptor testDescriptor) {
        // 这步判断是做啥的不理解。判断传入的方法是不是测试方法,不是测试方法就不进行处理
        if (!(testDescriptor instanceof TestMethodTestDescriptor)) {
            return FilterResult.includedIf(false);
        }
        // 当标签不存在
        if (!preFilter(this.selector)) {
            // 这里是放行
            return FilterResult.includedIf(true);
        } else {
            // 这部分是具体的标签筛选逻辑
            return onApply((TestMethodTestDescriptor) testDescriptor);
        }

    }
}
