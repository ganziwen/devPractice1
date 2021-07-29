package course.spring.spring_with_component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/28-10:42
 */
public class App {
    public static void main(String[] args) {
        // 理解把 xml 内的 bean 全部捞出来
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_with_component/beans.xml");
        // 需要用的 bean get 一下,返回的是 bean 也就是实现类
        FooService fooService =  applicationContext.getBean(FooService.class);
        String msg = fooService.foo1("spring_with_component");
        System.out.println("msg = " + msg);
    }
}
