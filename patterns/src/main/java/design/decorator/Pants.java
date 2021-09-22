package design.decorator;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/22-15:06
 */
public class Pants extends ClothDecorator {
    public Pants(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        return this.person.cost() + 300;
    }

    @Override
    public void show() {
        this.person.show();
        System.out.println("买了条裤子,累计消费" + this.cost());

    }
}
