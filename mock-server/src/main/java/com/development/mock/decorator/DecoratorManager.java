package com.development.mock.decorator;

import com.development.mock.model.HookContext;
import com.development.mock.model.MockContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:53
 */
public enum DecoratorManager {
    /**
     * 单例
     */
    newInstance;

    private IDecorator<String> packDecorator;
    private IDecorator<HookContext> hookDecorator;

    DecoratorManager() {
        this.packDecorator = new RandomIdDecorator(new RandomStrDecorator(null));
        this.hookDecorator = new CommHookDecorator(null);

    }

    public String doPack(String response) {
        return this.packDecorator.decorate(response);
    }

    public HookContext doHook(HookContext hookContext) {
        return this.hookDecorator.decorate(hookContext);
    }
}
