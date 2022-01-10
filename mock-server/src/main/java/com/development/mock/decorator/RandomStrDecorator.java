package com.development.mock.decorator;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:52
 */
public class RandomStrDecorator extends BasePackageResponseDecorator {
    public RandomStrDecorator(BasePackageResponseDecorator innerDecorator) {
        super(innerDecorator);
    }

    @Override
    protected String onDecorat(String data) {
        return null;
    }
}
