package course.aop.around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AccessLog
 * @Description
 * @date 2021/8/1 1:16
 */
@Component
@Aspect
public class AccessLog {
    @Pointcut("execution(public * course.aop.around.FooService.*(..))")
    public void accessLogPointCut() {

    }

    /**
     * 环绕通知
     */
    @Around("accessLogPointCut()")
    public Object accessLogAround(ProceedingJoinPoint point) {
        try {
            System.out.println("AccessLog.accessLogAround-before");
            // 这个代表方法内部的逻辑
            Object proceed = point.proceed();
            System.out.println("AccessLog.accessLogAround-after");
            // 返回方法的数据
            return proceed;
        } catch (Throwable throwable) {
            System.out.println("AccessLog.accessLogAround-error");
            throw new IllegalStateException(throwable);
        } finally {
            System.out.println("AccessLog.accessLogAround-finally");
        }
    }
}
