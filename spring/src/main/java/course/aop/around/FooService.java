package course.aop.around;

import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FooService
 * @Description
 * @date 2021/8/1 1:15
 */

@Component
public class FooService {
    public void foo1() {
        System.out.println("FooService.foo1");
    }
}
