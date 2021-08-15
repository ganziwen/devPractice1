package decorator.use_decorator;

import javafx.scene.layout.Pane;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PancakeDecorator
 * @Description
 * @date 2021/8/15 11:59
 */
abstract class PancakeDecorator implements IPancake {
    private IPancake iPancake;

    public PancakeDecorator(IPancake iPancake) {
        this.iPancake = iPancake;
    }

    @Override
    public String desc() {
        return this.iPancake.desc();
    }

    @Override
    public int cost() {
        return this.iPancake.cost();
    }
}

class EggDecoretor extends PancakeDecorator {
    public EggDecoretor(IPancake iPancake) {
        super(iPancake);
    }

    @Override
    public String desc() {
        return super.desc() + "加个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }
}

class SausageDecorator extends PancakeDecorator {
    public SausageDecorator(IPancake iPancake) {
        super(iPancake);
    }

    @Override
    public String desc() {
        return super.desc() + "加个肠";
    }

    @Override
    public int cost() {
        return super.cost() + 3;
    }
}