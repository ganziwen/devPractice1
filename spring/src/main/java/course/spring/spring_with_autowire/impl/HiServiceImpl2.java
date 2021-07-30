package course.spring.spring_with_autowire.impl;

import course.spring.spring_with_autowire.HiService;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-14:41
 */
@Component("HiServiceImpl2")
public class HiServiceImpl2 implements HiService {
    @Override
    public String sayHi(String msg) {
        return String.format("HiServiceImpl2.msg=%s", msg);
    }
}
