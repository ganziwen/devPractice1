package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LogAspect
 * @Description
 * @date 2022/3/13 16:41
 */
@Component
@Aspect
public class LogAspect {
    @Pointcut("execution(public * aop.*.*(..))")
    public void log() {
    }
    // 注意这里要写方法名
    @Before("log()")
    public void before(JoinPoint point) {
        String name = point.getSignature().getName();
        Object[] args = point.getArgs();
        String declaringTypeName = point.getSignature().getDeclaringTypeName();
        System.out.println("className:" + declaringTypeName);
        System.out.println("methodName:" + name);
        System.out.println("args:" + Arrays.toString(args));

    }

}
