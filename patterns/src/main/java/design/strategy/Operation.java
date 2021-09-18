package design.strategy;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/17-21:44
 */
@FunctionalInterface
public interface Operation {
    public int doOperation(int num1, int num2);
}
