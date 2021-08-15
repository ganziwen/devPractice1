package decorator.no_decorator;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description
 * @date 2021/8/15 11:44
 */
public class App {
    public static void main(String[] args) {
        testNoDecorator();
    }

    private static void testNoDecorator() {
        EggPancake eggPancake = new EggPancake();
        SausagePancake sausagePancake = new SausagePancake();
        System.out.println(eggPancake.desc() + "," + eggPancake.cost());
        System.out.println(sausagePancake.desc() + "," + sausagePancake.cost());
    }
}
