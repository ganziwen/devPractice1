package design.strategy;


/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/17-22:20
 */
public class Calculator {
    private Operation operation;

    // 通过 set 方法注入,把 Operation 注入到计算器内;其实也可以直接在构造器内注入.实现 Calculator 和 Operation 的解耦合
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    // 这里来操作计算器内的方法
    public int doOperation(int num1, int num2) {
        return this.operation.doOperation(num1, num2);
    }
}
