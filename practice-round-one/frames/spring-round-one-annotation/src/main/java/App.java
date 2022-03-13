import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;
import otherBean.SayHello;
import service.ClassOper;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description
 * @date 2022/3/13 10:33
 */
public class App {


    @Test
    public void testConfigurationBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfiguartion.class);
        SayHello sayHello = context.getBean(SayHello.class);
        String hello = sayHello.sayHelloToSomeOne("hello");
        System.out.println("hello = " + hello);
    }


    @Test
    public void testComponentScanBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfiguartion.class);
        ClassOper bean = context.getBean(ClassOper.class);
        String test = bean.getClassinfo("test");
        System.out.println("test = " + test);
    }


}
