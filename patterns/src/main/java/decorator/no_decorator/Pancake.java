package decorator.no_decorator;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Pancake
 * @Description
 * @date 2021/8/15 11:46
 */
abstract class Pancake {
    public String desc() {
        return "煎饼";
    }

    public int cost() {
        return 6;
    }
}

class EggPancake extends Pancake {
    @Override
    public String desc() {
        return super.desc() + "加个蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}
class SausagePancake extends Pancake {
    @Override
    public String desc() {
        return super.desc() + "加个肠";
    }

    @Override
    public int cost() {
        return super.cost() + 3;
    }
}