package strategy.strategy;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-17:19
 */
public class ConcreteStrategyB implements Strategy {
    @Override
    public void algorithm() {
        System.out.println("具体策略B的策略方法被访问！");
    }
}
