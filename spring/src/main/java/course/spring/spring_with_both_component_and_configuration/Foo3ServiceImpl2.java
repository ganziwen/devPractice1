package course.spring.spring_with_both_component_and_configuration;

import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-11:45
 * 问题点:假设两个 Component 同时在,怎么去找,会报 available: expected single matching bean but found 2
 */
// @Component
public class Foo3ServiceImpl2 implements Foo3Service {
    @Override
    public String foo3(String msgs) {

        return String.format("Foo3ServiceImpl2.foo3,msg=%s", msgs);
    }
}
