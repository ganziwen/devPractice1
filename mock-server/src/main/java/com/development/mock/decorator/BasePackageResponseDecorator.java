package com.development.mock.decorator;

import java.util.Objects;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:36
 */
public abstract class BasePackageResponseDecorator implements IDecorator<String> {

    private BasePackageResponseDecorator innerDecorator;

    public BasePackageResponseDecorator(BasePackageResponseDecorator innerDecorator) {
        this.innerDecorator = innerDecorator;
    }
    protected abstract String onDecorat(String data);


    @Override
    public String decorate(String data) {
        if (!Objects.isNull(this.innerDecorator)) {
            String result = this.innerDecorator.decorate(data);
            return onDecorat(result);
        }
        return onDecorat(data);
    }
}
