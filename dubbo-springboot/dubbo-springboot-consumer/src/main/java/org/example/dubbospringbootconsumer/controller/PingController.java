package org.example.dubbospringbootconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/9-11:36
 */
@RestController
public class PingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);

    @RequestMapping("/ping")
    public String ping() {
        LOGGER.info("请求正常");
        return "ping";
    }
}
