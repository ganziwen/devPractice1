package course.spring.spring_with_only_configuration;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/28-17:24
 * 注意这里还没使用 Component
 */

// @Component
public class Foo2ServiceImpl implements Foo2Service {
    @Override
    public String foo2(String msgs) {
        return String.format("FooServiceImpl.foo1:%s",msgs);
    }
}
