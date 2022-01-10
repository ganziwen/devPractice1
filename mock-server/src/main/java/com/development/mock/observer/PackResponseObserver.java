package com.development.mock.observer;

import com.development.mock.decorator.DecoratorManager;
import com.development.mock.model.MockContext;
import com.development.mock.util.RandomUtils;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PackResponseObserver
 * @Description 里面可能有很多需要替换的参数：${random:id},${random:str},${time} 等等
 * @date 2022/1/9 15:31
 */
public class PackResponseObserver implements IObserver<MockContext> {
    @Override
    public void update(MockContext mockContext) {
        String finalResponse = mockContext.getFinalResponse();
        /*
        一大堆的 if/else 就走下去了，可以用策略模式来整什么 time 之类的，因为逻辑比较轻量，如果逻辑比较复杂的话可以用责任链的方式;
        或者我们可以使用责任链的方式来玩
         */
        // if (finalResponse.contains("${random:id}")) {
        //     String newResponse = finalResponse.replaceAll("\\$\\{random:id}", RandomUtils.random32Id());
        //     mockContext.setFinalResponse(newResponse);
        // }

        String decorateResponse = DecoratorManager.newInstance.doPack(finalResponse);
        mockContext.setFinalResponse(decorateResponse);

    }
}
