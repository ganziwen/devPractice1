package course.spring.spring_with_only_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/28-17:28
 * 配置类,这个配置类相当于 beans.xml 配置文件
 */
@Configuration
public class Foo2Configuration {

    @Bean
    public Foo2Service foo2() {
        return new Foo2ServiceImpl();
    }
}
