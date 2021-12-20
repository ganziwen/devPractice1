package strategy.strategy;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/13-17:20
 */

public class Context {
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void algorithm() {
        strategy.algorithm();
    }

}
