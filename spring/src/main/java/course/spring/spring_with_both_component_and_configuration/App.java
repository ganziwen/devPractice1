package course.spring.spring_with_both_component_and_configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/29-15:49
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Foo3Configuration.class);
        Foo3Service foo3Service = context.getBean(Foo3Service.class);
        String msg = foo3Service.foo3("spring_with_both_component_and_configuration");
        System.out.println("msg = " + msg);
    }
}
