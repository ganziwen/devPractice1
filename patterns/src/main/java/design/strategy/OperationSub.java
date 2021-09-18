package design.strategy;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/17-22:29
 */
public class OperationSub implements Operation {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
