package com.example.mysqlschemasync.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Ping
 * @Description 测试通信的测试url
 * @date 2021/8/22 17:28
 */
@RestController
public class Ping {
    private static final Logger logger = LoggerFactory.getLogger(Ping.class);


    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        logger.info("ping start");
        return "ping";

    }
}
