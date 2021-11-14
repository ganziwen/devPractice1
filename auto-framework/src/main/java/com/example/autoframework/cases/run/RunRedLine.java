package com.example.autoframework.cases.run;

import com.example.autoframework.annotation.CaseSelector;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RunRedline
 * @Description
 * @date 2021/11/14 14:38
 */
public class RunRedLine {

    // 指定某个包下面，满足 key 和 value 的测试用例
    @CaseSelector(scanPackage = "com.example.autoframework.cases.pay", key = "level", val = "normal")
    public void runPayLine() {

    }
}
