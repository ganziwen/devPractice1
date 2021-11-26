package com.example.autoframework.cases.run;

import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.annotation.DingTalkAlarm;

import static com.example.autoframework.enums.Project.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RunRedline
 * @Description
 * @date 2021/11/14 14:38
 */
public class RunTagAndGroup {


    @CaseSelector(scanPackage = ACCOUNT_PACKAGE)
    public void runAccount1() {

    }

    // 指定某个包下面,满足 key 和 value 的测试用例
    @CaseSelector(scanPackage = ACCOUNT_PACKAGE, key = "level", val = LEVEL_NORMAL)
    @DingTalkAlarm(token = "test_token")
    public void runPayLine() {

    }


    @CaseSelector(scanPackage = ACCOUNT_PACKAGE, team = "test_team", group = "test_group")
    public void runAccount2() {

    }

    @CaseSelector(scanPackage = ACCOUNT_PACKAGE, team = "test_team", group = "test_group", key = "level", val = LEVEL_RED_LINE)
    public void runAccount3() {

    }

    // 用来指定做测试报告,可以做接口报警的机制,有异常就发送给dingding或者是其他的可以对接的邮件等
    @CaseSelector(scanPackage = ALARM_PACKAGE)
    public void ruReport() {

    }
}
