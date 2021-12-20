package factory.factoryMethod;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-17:00
 */
public class ConcreteFactoryB implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂B生成-->具体产品B.");
        return new ConcreteProductB();
    }
}
