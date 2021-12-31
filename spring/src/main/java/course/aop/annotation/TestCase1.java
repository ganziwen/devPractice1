package course.aop.annotation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/30-18:36
 */
@Component
public class TestCase1 {


    @TestAnnotation()
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConf.class);
        System.out.println("TestCase1.test1");
        // UserService userService = context.getBean(UserService.class);
        // userService.find();
    }
}
