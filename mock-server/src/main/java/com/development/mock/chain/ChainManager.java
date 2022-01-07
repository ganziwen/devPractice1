package com.development.mock.chain;

import com.development.mock.model.MockContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/7-11:48
 * 枚举替代单例
 */
public enum ChainManager {
    /**
     * 单例的instance
     */
    newInstance;

    private AbstractMockHandler<MockContext, String> mappingHandler;

    ChainManager() {
        this.mappingHandler = initMappingHandler();
    }

    private AbstractMockHandler<MockContext, String> initMappingHandler() {
        AbstractMockHandler<MockContext, String> singleMappingHandler = new SingleMappingHandler();
        AbstractMockHandler<MockContext, String> multipleMappingHandler = new MultipleMappingHandler();
        singleMappingHandler.setNextHandler(multipleMappingHandler);
        return singleMappingHandler;
    }

    public String doMapping(MockContext mockContext) {
        return this.mappingHandler.doHandle(mockContext);
    }
}
