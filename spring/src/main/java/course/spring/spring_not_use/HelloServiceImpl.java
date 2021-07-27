package course.spring.spring_not_use;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/27-10:30
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHi(String msg) {
        return String.format("hi:%s", msg);
    }
}
