package course.spring.spring_with_autowire.impl;

import course.spring.spring_with_autowire.HelloService;
import course.spring.spring_with_autowire.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-14:09
 */
@Component
public class HelloServiceImpl implements HelloService {

    // 相当于实例化了一个 HiService 的实现类,不加 Autowire 的话,直接用肯定会报空指针,有这个注解的话,spring 就会帮我们去找 HiService 这个接口对应的实现类,注入到 hiService 这个属性内
    @Autowired
    // 制定 bean 的 id 来调用
    @Qualifier("HiServiceImpl2")
    private HiService hiService;

    @Override
    public String sayHello(String msg) {
        System.out.println(hiService.sayHi("Hi"));

        return String.format("HelloServiceImpl.msg=%s", msg);
    }
}
