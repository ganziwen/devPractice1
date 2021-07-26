package factory;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-16:43
 */
public class Cat implements Animal {
    @Override
    public void eat() {
        System.out.println("Cat.eat");
    }

    @Override
    public void run() {
        System.out.println("Cat.run");
    }
}
