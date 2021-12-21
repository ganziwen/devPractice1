package com.example.autoframework.cases.run;

import com.example.autoframework.alarm.callback.DefaultAlarmCallback;
import com.example.autoframework.annotation.CaseSelector;
import com.example.autoframework.annotation.DingTalkAlarm;
import com.example.autoframework.annotation.ReportConfig;
import com.example.autoframework.report.callback.DefaultReportCallback;

import static com.example.autoframework.enums.Project.DING_DING_TOKEN;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/25-17:55
 */
public class RunAlarmCases {
    @CaseSelector(scanPackage = "com.example.autoframework.cases.alarm")
    @DingTalkAlarm(token = DING_DING_TOKEN, callback = DefaultAlarmCallback.class)
    @ReportConfig(token = DING_DING_TOKEN, callback = DefaultReportCallback.class)
    public void testSelect1() {
        // assertThat(1).isEqualTo(0);

    }


    // 指定某个包下面,满足 key 和 value 的测试用例
    @CaseSelector(scanPackage = "com.example.autoframework.cases.accout")
    public void runAccount3() {

    }
}
