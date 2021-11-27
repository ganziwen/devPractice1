package com.example.autoframework.alarm.service;

import com.example.autoframework.model.FailureResult;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AlarmService
 * @Description 这里才是具体的告警逻辑，不管是单个还是多个用例的失败
 * @date 2021/11/27 17:50
 */
public class AlarmService {
    public void doAlarm(FailureResult failureResult) {
        System.out.println("这是告警的内容");
        // 报警逻辑，此刻面临两个问题
        // 1. 用例标题等信息去哪获取？(从注解内获取)
        // 2. 用例告警的信息怎么组装（创建一个发送格式的模板）
        // 告警的内容：
        // 用例 id：xxx
        // 用例标题：xxx
        // 用例xxx：xxx

    }
}
