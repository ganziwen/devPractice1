package org.example.dubbospringbootconsumer.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.dubbospringbootprovider.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/9-17:43
 */

@RestController
public class DemoServiceController {
    @DubboReference
    private DemoService demoService;

    @RequestMapping("/hello")
    public String demoService(@RequestParam("name") String name) {
        return demoService.sayHello(name);
    }
}
