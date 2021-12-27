package com.development.mock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PingController
 * @Description
 * @date 2021/12/26 15:47
 */
@RestController
public class PingController {

    @RequestMapping("ping")
    public String ping() {

        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            throw new IllegalStateException(e);
        }
    }
}
