package course.aop.aop;

import course.spring.spring_with_only_configuration.Foo2Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description
 * @date 2021/7/31 10:44
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
        FooService bean = context.getBean(FooService.class);
        bean.foo1();

        Foo2ServiceOtherJar foo2ServiceOtherJar = context.getBean(Foo2ServiceOtherJar.class);
        foo2ServiceOtherJar.foo2();

    }
}
