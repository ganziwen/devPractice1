package design.decorator;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/18-14:54
 * 装饰器-衬衫
 */
public class Shirt extends ClothDecorator {
    // 构造的时候就需要把小明给注入进来
    public Shirt(Person person) {
        super(person);
    }

    @Override
    public Double cost() {
        return this.person.cost() + 100;
    }

    @Override
    public void show() {
        // 展示之前的 show 方法结果
        this.person.show();
        System.out.println("买了件衬衫,累计消费" + this.cost());
    }
}
