package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PingController
 * @Description
 * @date 2021/10/31 12:36
 */
@RestController
public class PingController {
    private static final Logger logger = LoggerFactory.getLogger(PingController.class);

    @RequestMapping("/ping")
    public String ping() {
        logger.info("ping 完成");
        return "ping";
    }
}
