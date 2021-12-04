package com.example.autoframework.alarm.service;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.log.StaticLog;
import com.example.autoframework.annotation.CaseDesc;
import com.example.autoframework.annotation.CaseTitle;
import com.example.autoframework.annotation.CheckPoint;
import com.example.autoframework.annotation.CheckPoints;
import com.example.autoframework.model.FailureResult;
import com.example.autoframework.template.TemplateFacade;
import com.example.autoframework.util.ReflectUtils;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AlarmService
 * @Description 这里才是具体的告警逻辑，不管是单个还是多个用例的失败
 * @date 2021/11/27 17:50
 */
public class AlarmService {
    public void doAlarm(FailureResult failureResult) {
        // StaticLog.info("这是告警的内容");
        // StaticLog.warn(failureResult.toString());
        // 报警逻辑，此刻面临两个问题
        // 1. 用例标题等信息去哪获取？(从注解内获取)
        // 2. 用例告警的信息怎么组装（创建一个发送格式的模板）
        // 告警的内容：
        // 用例 id：xxx
        // 用例标题：xxx
        // 用例xxx：xxx

        String title = "无";
        String desc = "无";
        String owner = "无";
        String check = "无";
        // 用例id
        String caseId = String.format("%s#%s", failureResult.getClassName(), failureResult.getMethodName());
        // 报错信息
        String cause = failureResult.getThrowable() == null ? "无" : failureResult.getThrowable().getMessage();

        // 基于 className 和 methodName 用反射获取到 java.lang.reflect.Method
        // Method testMethod = ReflectUtil.getMethodOfObj(failureResult, failureResult.getMethodName());
        // Method testMethod = ReflectUtil.getMethod(failureResult.getClass(), failureResult.getMethodName());
        // Method testMethod = ReflectUtil.getMethod(ReflectUtil.newInstance(failureResult.getClassName()).getClass(), failureResult.getMethodName());
        Method testMethod = ReflectUtils.getMethod(failureResult.getClassName(), failureResult.getMethodName());
        boolean isCaseTitleSet = testMethod.isAnnotationPresent(CaseTitle.class);
        if (isCaseTitleSet) {
            CaseTitle caseTitle = testMethod.getAnnotation(CaseTitle.class);
            title = caseTitle.value();

        }

        boolean isCaseDescSet = testMethod.isAnnotationPresent(CaseDesc.class);
        if (isCaseDescSet) {
            CaseDesc caseDesc = testMethod.getAnnotation(CaseDesc.class);
            desc = caseDesc.desc();
            owner = caseDesc.owner();
        }
        // 这里要注意是判断 CheckPoints 注解有没有
        boolean isCheckPointSet = testMethod.isAnnotationPresent(CheckPoints.class);
        if (isCheckPointSet) {
            CheckPoint[] checkPoints = testMethod.getAnnotationsByType(CheckPoint.class);
            check = Arrays.stream(checkPoints).map(CheckPoint::value).collect(Collectors.joining(","));

        }

        // 开始封装成个 map
        HashMap<String, String> resultMap = Maps.newHashMap();
        resultMap.put("title", title);
        resultMap.put("desc", desc);
        resultMap.put("owner", owner);
        resultMap.put("check", check);
        resultMap.put("caseId", caseId);
        resultMap.put("cause", cause);

        String alarmTemplateRes = TemplateFacade.replaceTemplate("default_alarm_template", resultMap);

        // 将此消息发出去
        StaticLog.info(alarmTemplateRes);
    }
}
