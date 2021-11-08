import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DemoService;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/8-17:09
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        // 启动 consumer
        applicationContext.start();
        DemoService demoService = (DemoService) applicationContext.getBean("demoService");
        final String hello = demoService.sayHello("ganziwen");
        System.out.println("hello = " + hello);
    }
}
