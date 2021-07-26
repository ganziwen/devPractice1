package factory;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-16:43
 */
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog.eat");
    }

    @Override
    public void run() {
        System.out.println("Dog.run");
    }
}
