package course.spring.spring_with_both_component_and_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;


/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-11:19
 * 一般一个项目写一个就够了
 * ComponentScan 后面的 base 其实是可以省略的,表示会扫描当前类所在包下所有的 Component,即使不在同一级别目录下
 * ComponentScan 是可以写多个的
 */

@Configuration
@ComponentScan(basePackages = {"course.spring.spring_with_both_component_and_configuration"})
public class Foo3Configuration {
    /**
     * 这就是很明显的例子,LocalDateTime 的方法没有 @Component 注解,就必须得去自己定义
     *
     * @return
     */
    @Bean
    public LocalDateTime getTime() {
        return LocalDateTime.now();
    }
}
