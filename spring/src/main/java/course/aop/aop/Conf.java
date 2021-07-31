package course.aop.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Conf
 * @Description
 * @date 2021/7/31 10:46
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy // 自动完成代理
public class Conf {
    @Bean
    public Foo2ServiceOtherJar foo2ServiceOtherJar() {
        return new Foo2ServiceOtherJar();
    }
}
