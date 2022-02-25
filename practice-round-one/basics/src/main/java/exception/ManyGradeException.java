package exception;

import cn.hutool.core.io.file.PathUtil;
import javafx.animation.PauseTransition;
import org.testng.annotations.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ManyGradeException
 * @Description 多级异常
 * @date 2022/2/25 22:53
 */
public class ManyGradeException {
    public void pay(double num) throws TimeOutEx, PaySysEx, BalanceEx {
        System.out.println("num = " + num);
    }

    /**
     * 实际用法
     */
    @Test
    public void testPay() {
        try {
            pay(666L);
        } catch (TimeOutEx timeOutEx) {
            timeOutEx.printStackTrace();
        } catch (PaySysEx paySysEx) {
            paySysEx.printStackTrace();
        } catch (BalanceEx balanceEx) {
            balanceEx.printStackTrace();
        }
    }

    /**
     * 优化写法
     */
    @Test
    public void testPayWithEx() {
        try {
            pay(110L);
        } catch (Exception e) {
            // 转出处理
            handleExceptions(e);
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println("stackTraceElement.getClassName() = " + stackTraceElement.getClassName());
                System.out.println("stackTraceElement.getMethodName() = " + stackTraceElement.getMethodName());
                System.out.println("stackTraceElement.getLineNumber() = " + stackTraceElement.getLineNumber());
                System.out.println("stackTraceElement.getFileName() = " + stackTraceElement.getFileName());
            }
        }
    }

    public void handleExceptions(Exception e) {
        if (e instanceof TimeOutEx) {
            System.out.println("TimeOutEx error");
        } else if (e instanceof PaySysEx) {
            System.out.println("PaySysEx error");
        } else if (e instanceof BalanceEx) {
            System.out.println("BalanceEx error");
        }
    }


}


class TimeOutEx extends Exception {
    // 网络超时
}

class PaySysEx extends Exception {
    // 余额不足
}

class BalanceEx extends Exception {
    // 支付系统崩溃
}