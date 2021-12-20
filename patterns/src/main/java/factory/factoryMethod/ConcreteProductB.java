package factory.factoryMethod;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-16:57
 */
public class ConcreteProductB implements Product {
    @Override
    public void use() {
        System.out.println("具体产品B显示...");
    }
}
