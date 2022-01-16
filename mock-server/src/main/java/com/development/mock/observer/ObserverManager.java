package com.development.mock.observer;

import com.development.mock.model.MockContext;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ObserverManager
 * @Description
 * @date 2022/1/8 15:43
 */
public enum ObserverManager {
    /*
    枚举实现单例
     */
    newInstance;

    private final List<IObserver<MockContext>> observers;


    ObserverManager() {
        /*
        1. 加载本地的 mock 文件，将其数据转化成我们需要的数据对象 -> LoadMockFilesObserver
        2. 基于请求的参数集合来计算第一步中的所有匹配的数据权重加和 -> CalcWeightObserver
        3. 处理数据……
         */
        this.observers = Lists.newArrayList(
                // 第一步加载本地 mock 文件并解析出我们需要的数据模型
                new LoadMockFilesObserver(),
                // 第二步计算权重，找到最大的那个
                new CalcWeightObserver(),
                // 第三步包装返回数据
                new PackResponseObserver(),
                // 第四步处理透传数据
                new HookResponseObserver()
        );
    }

    public String getMockData(MockContext mockContext) {
        for (IObserver<MockContext> observer : observers) {
            observer.update(mockContext);
        }
        return mockContext.getFinalResponse();
    }
}
