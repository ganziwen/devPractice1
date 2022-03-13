package aop;

import aop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName UserInfoController
 * @Description
 * @date 2022/3/13 16:08
 */
@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    public void userInfo(String name, int age) {
        System.out.println("UserInfoController.userInfo");
        userInfoService.serviceInfo(name, age);
    }

}
