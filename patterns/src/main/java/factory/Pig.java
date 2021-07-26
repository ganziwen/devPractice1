package factory;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-18:03
 */
public class Pig implements Animal {
    @Override
    public void eat() {
        System.out.println("Pig.eat");
    }

    @Override
    public void run() {
        System.out.println("Pig.run");
    }
}
