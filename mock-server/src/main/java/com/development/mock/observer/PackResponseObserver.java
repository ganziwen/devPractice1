package com.development.mock.observer;

import com.development.mock.model.MockContext;

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
        if (finalResponse.contains("${random:id}")) {
            finalResponse.replaceAll("\$\{random\:id\}", "")
        }
    }
}
