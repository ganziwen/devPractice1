package com.development.mock.decorator;

import java.util.Objects;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:36
 */
public abstract class BaseResponseDecorator<T> implements IDecorator<T> {

    private BaseResponseDecorator<T> innerDecorator;

    public BaseResponseDecorator(BaseResponseDecorator<T> innerDecorator) {
        this.innerDecorator = innerDecorator;
    }
    protected abstract T onDecorate(T data);


    @Override
    public T decorate(T data) {
        if (!Objects.isNull(this.innerDecorator)) {
            T result = this.innerDecorator.decorate(data);
            return onDecorate(result);
        }
        return onDecorate(data);
    }
}
