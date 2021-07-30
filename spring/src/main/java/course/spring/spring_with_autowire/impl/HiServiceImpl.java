package course.spring.spring_with_autowire.impl;

import course.spring.spring_with_autowire.HiService;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-14:12
 */

// Component 默认的 id 是类名的首字母转小写也就是 hiServiceImpl
@Component("HiServiceImpl")
public class HiServiceImpl implements HiService {
    @Override
    public String sayHi(String msg) {
        return String.format("HiServiceImpl.msg=%s", msg);
    }
}
