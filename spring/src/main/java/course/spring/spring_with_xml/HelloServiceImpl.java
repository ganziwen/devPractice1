package course.spring.spring_with_xml;

import lombok.Data;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/27-11:06
 */

@Data
public class HelloServiceImpl implements HelloService {
    private String name;
    private int age;

    @Override
    public String sayHello(String msg) {
        return String.format("hello:%s,name=%s,age=%s", msg,name,age);
    }
}
