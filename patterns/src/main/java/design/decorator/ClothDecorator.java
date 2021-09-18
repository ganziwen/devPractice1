package design.decorator;

import com.sun.scenario.effect.impl.prism.PrImage;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/18-14:51
 * 装饰器
 */
public abstract class ClothDecorator implements Person {
    protected Person person;

    public ClothDecorator(Person person) {
        this.person = person;
    }
}
