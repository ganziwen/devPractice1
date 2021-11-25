package com.example.autoframework.cases.run;

import com.example.autoframework.annotation.CaseSelector;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/25-17:55
 */
public class RunAlarmCases {
    @CaseSelector(scanPackage = "com.example.autoframework.cases.alarm")
    public void testSelect1() {

    }


    // 指定某个包下面,满足 key 和 value 的测试用例
    @CaseSelector(scanPackage = "com.example.autoframework.cases.accout")
    public void runAccount3() {

    }
}
