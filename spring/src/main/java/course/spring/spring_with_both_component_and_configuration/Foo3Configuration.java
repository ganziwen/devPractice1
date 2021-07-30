package course.spring.spring_with_both_component_and_configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-11:19
 * 一般一个项目写一个就够了
 */

@Configuration
@ComponentScan(basePackages = {"course.spring.spring_with_both_component_and_configuration"})
public class Foo3Configuration {
}
