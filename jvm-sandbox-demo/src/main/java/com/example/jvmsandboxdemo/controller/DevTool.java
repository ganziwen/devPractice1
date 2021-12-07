package com.example.jvmsandboxdemo.controller;

import com.example.jvmsandboxdemo.service.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/7-14:08
 * 这里只是执行简单的两台服务器之间的文件传输并且解压
 */

@RestController
@RequestMapping("/OpenAppsLog")
public class DevTool {

    @Autowired
    DevService devService;

    public void openAppLogs() {
        String host = "";
        devService.openLog(host);

    }
}
