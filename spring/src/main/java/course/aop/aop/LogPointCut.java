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

    /**
     * 这个就是切点
     * 定义往需要加日志的范围
     * public 代表方法的权限修饰符
     * 后面是返回类型, * 代表所有,是一个通配符
     * 再后面是跟着类,方法
     * (..)代表的是方法参数, .. 代表通配
     */
    @Pointcut("execution(public * course.aop.aop.*.*(..))")
    public void logPointcut() {

    }

    /**
     * 前置通知
     * 注意 logPointcut 需要加 ()
     */
    @Before("logPointcut()")
    public void logBefore() {
        System.out.println("---LogPointCut.logBefore---");
    }

    /**
     * 后置通知
     */
    @After("logPointcut()")
    public void logAfter() {
        System.out.println("---LogPointCut.logAfter---");
    }


    /**
     * 异常通知
     */
    @AfterThrowing("logPointcut()")
    public void afterThrow() {
        System.out.println("---LogPointCut.afterThrow---");
    }
}
