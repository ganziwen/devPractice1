package com.development.mock.decorator;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:33
 */
public interface IDecorator<T> {
    T decorate(T data);
}
