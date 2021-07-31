package course.aop.aop;

import org.aspectj.lang.annotation.*;
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
@Aspect // 切面
public class LogPointCut {

    // 切点
    @Pointcut("execution(public * course.aop.aop.*.*(..))")
    public void logPointcut() {

    }

    /**
     * 切前面
     * 注意 logPointcut 需要加 ()
     */
    @Before("logPointcut()")
    public void logBefore() {
        System.out.println("---LogPointCut.logBefore---");
    }

    /**
     * 切后面
     */
    @After("logPointcut()")
    public void logAfter() {
        System.out.println("---LogPointCut.logAfter---");
    }


    /**
     * 异常的时候
     */
    @AfterThrowing("logPointcut()")
    public void afterThrow() {
        System.out.println("---LogPointCut.afterThrow---");
    }
}
