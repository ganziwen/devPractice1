package course.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LogPointCut
 * @Description
 * @date 2021/7/31 10:51
 */
@Component
@Aspect
public class LogPointCut {

    @Pointcut("execution(public * course.aop.aop.*.*(..))")
    public void pointcut() {

    }

    // 注意要把 () 带上
    @Before("pointcut()")
    public void logBefore() {
        System.out.println("---LogPointCut.logBefore---");
    }
}
