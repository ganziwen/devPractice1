package com.example.autoframework.alarm;

import com.example.autoframework.alarm.service.AlarmService;
import com.example.autoframework.model.FailureResult;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/25-16:23
 * facade 不做具体的逻辑，只做门面
 */
public class AlarmFacade {
    public static void doAlarm(FailureResult failureResult) {
        new AlarmService().doAlarm(failureResult);
    }
}
