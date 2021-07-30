package course.spring.spring_with_value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FooServiceImpl
 * @Description
 * @date 2021/7/30 22:07
 */

@Component
public class FooServiceImpl implements FooService {

    @Autowired
    private Person person;


    @Value("180")
    private int height;

    @Override
    public void foo() {
        System.out.println(String.format("FooServiceImpl.foo,name=%s,age=%s,height=%s", person.name, person.age, height));
    }
}
