import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/8-17:27
 */
public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider.xml");
        // 启动 provider
        applicationContext.start();
        System.in.read();
    }
}
