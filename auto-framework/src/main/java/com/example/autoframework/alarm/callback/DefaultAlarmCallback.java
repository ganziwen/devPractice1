package com.example.autoframework.alarm.callback;

import com.example.autoframework.alarm.AlarmFacade;
import com.example.autoframework.model.FailureResult;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DefaultAlarmCallback
 * @Description
 * @date 2021/11/27 16:30
 */
public class DefaultAlarmCallback implements AlarmCallback {
    // 在这里进行处理告警信息
    @Override
    public void postExecutionFailure(FailureResult failureResult) {
        // System.out.println("this is my alarmcallback");
        // System.out.println("failureResult = " + failureResult);

        AlarmFacade.doAlarm(failureResult);
    }
}
