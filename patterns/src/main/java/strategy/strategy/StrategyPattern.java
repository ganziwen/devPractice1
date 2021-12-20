package strategy.strategy;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-17:21
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context();
        Strategy strategyA = new ConcreteStrategyA();
        context.setStrategy(strategyA);
        context.algorithm();
        System.out.println("-----------------");
        Strategy strategyB = new ConcreteStrategyB();
        context.setStrategy(strategyB);
        context.algorithm();
    }
}
