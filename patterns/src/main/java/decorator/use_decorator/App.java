package decorator.use_decorator;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description
 * @date 2021/8/15 12:04
 */
public class App {
    public static void main(String[] args) {

        IPancake p1 = new EggDecoretor(new EggDecoretor(new SausageDecorator(new Pancake())));

        System.out.println(p1.desc() + " 需要 " + p1.cost() + " 元");
    }
}
