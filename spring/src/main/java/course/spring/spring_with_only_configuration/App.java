package course.spring.spring_with_only_configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/28-17:24
 */
public class App {
    public static void main(String[] args) {
        // 利用 Configuration 加载 bean
        ApplicationContext context = new AnnotationConfigApplicationContext(Foo2Configuration.class);
        Foo2Service foo2Service = context.getBean(Foo2Service.class);
        String msg = foo2Service.foo2("spring_with_configuration");
        System.out.println("msg = " + msg);
    }
}
