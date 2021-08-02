package com.learn.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PingController
 * @Description
 * @date 2021/8/1 2:22
 */

@RestController
public class PingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);

    @RequestMapping("/ping")
    public String ping() {
        LOGGER.info("ping start!");
        return "ping";
    }
}
