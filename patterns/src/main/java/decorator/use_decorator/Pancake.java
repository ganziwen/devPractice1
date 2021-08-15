package decorator.use_decorator;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Pancake
 * @Description
 * @date 2021/8/15 12:09
 */
public class Pancake implements IPancake {
    @Override
    public String desc() {
        return "煎饼";
    }

    @Override
    public int cost() {
        return 6;
    }
}
