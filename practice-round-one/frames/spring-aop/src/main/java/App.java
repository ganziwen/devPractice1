import aop.UserInfoController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description
 * @date 2022/3/13 16:32
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UserComponentScan.class);

        UserInfoController userInfoController = applicationContext.getBean(UserInfoController.class);

        userInfoController.userInfo("ganziwen", 19);
    }
}
