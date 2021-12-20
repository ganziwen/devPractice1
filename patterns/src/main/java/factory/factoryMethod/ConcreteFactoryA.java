package factory.factoryMethod;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-17:00
 */
public class ConcreteFactoryA implements Factory {
    @Override
    public Product createProduct() {
        System.out.println("具体工厂A生成-->具体产品A.");
        return new ConcreteProductA();
    }
}
