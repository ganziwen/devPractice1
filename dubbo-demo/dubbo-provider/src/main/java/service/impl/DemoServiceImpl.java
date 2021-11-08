package service.impl;

import service.DemoService;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/8-16:52
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
