package com.example.mysqlschemasync.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Ping
 * @Description
 * @date 2021/8/22 17:28
 */
@RestController
public class Ping {
    @RequestMapping("/ping")
    public String ping() {
        String str = "ping";
        return str;

    }
}
