package com.example.autoframework.alarm.callback;

import com.example.autoframework.model.FailureResult;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AlarmCallback
 * @Description
 * @date 2021/11/27 16:18
 */
public interface AlarmCallback {

    void postExecutionFailure(FailureResult failureResult);
}
