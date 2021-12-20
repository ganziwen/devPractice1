package factory.factoryMethod;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-16:55
 */
public class FactoryPattern {
    public static void main(String[] args) {
        Factory factory = new ConcreteFactoryA();
        Product product = factory.createProduct();
        product.use();

    }
}
