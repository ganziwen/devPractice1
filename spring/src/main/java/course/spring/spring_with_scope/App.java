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
        // 其实实例化出了容器,那么 bean 都会被实例化出来
        ApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
        ApplicationContext context2 = new AnnotationConfigApplicationContext(Conf.class);

        // 单例由 scope 控制,单例的话,构造器只会调用一次;并不是针对全进程级别的单例而是针对单容器的单例
        // FooScopeService bean1 = context.getBean(FooScopeService.class);
        // FooScopeService bean2 = context.getBean(FooScopeService.class);
        //
        // bean1.fooScope();
        // bean2.fooScope();
        //
        //
        // // 这也是个容器
        // FooScopeService bean3 = context2.getBean(FooScopeService.class);
        // bean3.fooScope();


    }
}
