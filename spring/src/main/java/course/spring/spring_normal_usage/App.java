package course.spring.spring_normal_usage;

import course.spring.spring_normal_usage.service.PingService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-15:15
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        PingService bean = context.getBean(PingService.class);
        String msg = bean.ping("outmsg");
        System.out.println("msg = " + msg);
    }
}
