package org.example.dubbospringbootprovider.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.dubbospringbootprovider.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/9-12:12
 */
@Service
@DubboService(timeout = 2000, retries = 5, version = "1.0.0")// 这个是 dubbo 的 service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "provider say hello" + name;
    }
}
