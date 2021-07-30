package course.spring.spring_with_scope;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-16:14
 */
@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
// @Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)
public class FooScopeServiceImpl implements FooScopeService {

    public FooScopeServiceImpl() {
        System.out.println("FooScopeServiceImpl.FooScopeServiceImpl---init---");
    }

    @Override
    public void fooScope() {
        System.out.println("FooScopeServiceImpl.fooScope");
    }

}
