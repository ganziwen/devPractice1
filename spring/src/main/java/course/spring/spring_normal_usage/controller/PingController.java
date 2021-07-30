package course.spring.spring_normal_usage.controller;

import course.spring.spring_normal_usage.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-15:16
 */
@Controller
public class PingController {
    @Autowired
    private PingService pingService;


    public String ping(String msg) {
        return String.format("PingController.msg=%s,PingService=%s", msg, pingService.ping("PingController/pingService"));

    }
}
