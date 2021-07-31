package course.aop.aop;

import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FooServiceImpl
 * @Description
 * @date 2021/7/31 10:45
 */

@Component
public class FooServiceImpl implements FooService {
    @Override
    public void foo1() {
        System.out.println("FooServiceImpl.foo1");
        // int i = 1 / 0;
    }
}
