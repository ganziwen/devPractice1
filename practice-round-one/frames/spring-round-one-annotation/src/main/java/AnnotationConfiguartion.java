import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import otherBean.SayHello;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Configuartion
 * @Description
 * @date 2022/3/13 10:27
 */
// @Configuration 用来指定一个方法生成 bean
// @ComponentScan 扫描指定路径下的注解生成 bean

@Configuration
@ComponentScan(basePackages = "service")
public class AnnotationConfiguartion {


    // 将非 component 的进行 bean 注解,作为本项目的 bean 进行使用,主要注意的是，这种方式是通过构造对象的方式来使用的
    @Bean
    public SayHello sayHelloBean() {
        return new SayHello();
    }

    @Bean
    public List<String> getList() {
        return Lists.newArrayList("aa", "bb");
    }

}
