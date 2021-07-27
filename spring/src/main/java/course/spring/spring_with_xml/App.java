package course.spring.spring_with_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/27-11:07
 * Spring 的 xml 方式
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_with_xml/beans.xml");

        // 我们并不知道 HelloService 的具体实现类是哪一个,用哪一个是在 beans.xml 里面配置的
        HelloService helloService = context.getBean(HelloService.class);

        // 或者用 id
        // HelloService helloService = (HelloService) context.getBean("HelloService");

        String msg = helloService.sayHello("Spring Xml");
        System.out.println("msg = " + msg);
    }
}
