package design.decorator;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/18-14:48
 * 被装饰对象
 */
public class Xiaoming implements Person {

    @Override
    public Double cost() {
        return 0.0;
    }

    @Override
    public void show() {
        System.out.println("小明走进商场,啥都没买");
    }
}
