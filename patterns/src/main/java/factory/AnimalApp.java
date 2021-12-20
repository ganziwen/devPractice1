package factory;

import org.junit.Test;

import java.util.Optional;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-16:18
 * 工厂设计模式
 */
public class AnimalApp {

    /**
     * 最 low 的方式
     */
    @Test
    public void testNotFactory() {
        // 创建对象与使用过程无法拆开,假设我要把 Dog 变成 Cat ,就必须改代码
        Animal animal = new Dog();
        animal.eat();
        animal.run();
    }

    /**
     * 利用单例进行优化
     */
    @Test
    public void testFactorySimple() {
        // 实例化的话,需改改传入的 description 参数的值即可
        Animal animal = AnimalFactory.of().createAnimalInstance("cat");
        animal.eat();
        animal.run();
    }

    /**
     * 加入反射
     */
    @Test
    public void testFactoryWithReflect() {
        String className = "factory.Pig";
        Optional<Animal> animal = AnimalFactory.of().createAnimalInstanceWithReflect(className);
        if (animal.isPresent()) {
            animal.get().eat();
            animal.get().run();
        }
    }
}

