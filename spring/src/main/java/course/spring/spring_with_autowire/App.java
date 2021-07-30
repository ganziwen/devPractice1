package course.spring.spring_with_autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-14:08
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        HelloService helloService = context.getBean(HelloService.class);
        String msg = helloService.sayHello("Hello");
        System.out.println("msg = " + msg);
    }
}
