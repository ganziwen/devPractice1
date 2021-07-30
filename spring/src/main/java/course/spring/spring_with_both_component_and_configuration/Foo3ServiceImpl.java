package course.spring.spring_with_both_component_and_configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/29-15:50
 * @Component 标识把类放到 Spring 容器内管理,相当于 <bean id="Foo3ServiceImpl" class="course.spring.spring_with_both_component_and_configuration.Foo3ServiceImpl">
 */
@Component
public class Foo3ServiceImpl implements Foo3Service {

    @Autowired
    private LocalDateTime now;

    @Override
    public String foo3(String msgs) {
        return String.format("Foo3ServiceImpl.foo3,msg=%s,now=%s", msgs, now);
    }
}
