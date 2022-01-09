package com.development.mock.observer;

import com.development.mock.model.MockContext;
import org.assertj.core.util.Lists;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

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
                new LoadMockFilesObserver(),
                new CalcWeightObserver(),
                new PackResponseObserver()
        );
    }

    public String getMockData(MockContext mockContext) {
        for (IObserver<MockContext> observer : observers) {
            observer.update(mockContext);
        }
        return mockContext.getFinalResponse();
    }
}
