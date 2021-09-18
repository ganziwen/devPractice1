package design.strategy;

import com.sun.corba.se.impl.oa.poa.POAPolicyMediatorImpl_NR_UDS;
import org.junit.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/17-22:29
 * 策略模式
 */

public class strategyTest {

    @Test
    public void test1() {
        // 初始化构造一个空的计算器
        Calculator calculator = new Calculator();
        // 给计算器注入加法运算,此时计算器就是执行加法运算
        calculator.setOperation(new OperationAdd());
        System.out.println("calculator.doOperation(1, 2) = " + calculator.doOperation(1, 2));

        // 给计算器注入减法运算,此时计算器就是执行减法运算
        calculator.setOperation(new OperationSub());
        System.out.println("calculator.doOperation(2,1) = " + calculator.doOperation(2, 1));

        // 甚至是注入乘法能力
        calculator.setOperation((num1, num2) -> num1 * num2);

        System.out.println("calculator.doOperation(2,2) = " + calculator.doOperation(2, 2));

    }
}
