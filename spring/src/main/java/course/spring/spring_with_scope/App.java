package course.spring.spring_with_scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-16:15
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        // 单例由 scope 控制,单例的话,构造器只会调用一次
        FooScopeService bean1 = context.getBean(FooScopeService.class);
        FooScopeService bean2 = context.getBean(FooScopeService.class);
        FooScopeService bean3 = context.getBean(FooScopeService.class);
        FooScopeService bean4 = context.getBean(FooScopeService.class);
        bean1.fooScope();
        bean2.fooScope();
        bean3.fooScope();
        bean4.fooScope();
    }
}
