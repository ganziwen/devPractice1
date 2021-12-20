package strategy.strategy;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-17:19
 */
public class ConcreteStrategyA implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}
