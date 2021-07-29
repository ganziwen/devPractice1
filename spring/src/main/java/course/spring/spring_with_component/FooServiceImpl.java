package course.spring.spring_with_component;

import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/28-10:44
 * @Component 标识把类放到 Spring 容器内管理,相当于 <bean id="fooServiceImpl" class="course.spring.spring_with_component.FooServiceImpl">
 */
@Component
public class FooServiceImpl implements FooService {

    @Override
    public String foo1(String msg1) {
        return String.format("FooServiceImpl.foo1,msg=%s",msg1);
    }
}
