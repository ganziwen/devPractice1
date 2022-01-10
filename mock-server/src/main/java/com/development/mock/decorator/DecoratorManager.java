package com.development.mock.decorator;

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

    DecoratorManager() {
        this.packDecorator = new RandomIdDecorator(new RandomStrDecorator(null));

    }

    public String doPack(String response) {
        return this.packDecorator.decorate(response);
    }
}
