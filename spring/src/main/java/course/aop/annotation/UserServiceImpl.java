package course.aop.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-10:33
 */
@Component
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("UserServiceImpl.save");
    }

    @Override
    public void delete() {
        System.out.println("UserServiceImpl.delete");
    }

    @Override
    public void update() {
        System.out.println("UserServiceImpl.update");
    }

    @TestAnnotation
    @Override
    public void find() {
        System.out.println("UserServiceImpl.find");
    }
}
