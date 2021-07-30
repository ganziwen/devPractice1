package course.spring.spring_with_only_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/28-17:28
 * 配置类,这个配置类相当于 beans.xml 配置文件
 * 这种方式是用来引用别人写的类,放到 bean 内管理,因为别人写的抽象方法,不一定会加上 @Component 管理
 */
@Configuration
public class Foo2Configuration {

    // 相当于在 bean 内加了 <bean id="foo2" class="course.spring.spring_with_only_configuration.Foo2ServiceImpl">
    // 同时在 Foo2ServiceImpl 上加了 Component
    @Bean
    public Foo2Service foo2() {
        return new Foo2ServiceImpl();
    }
}
