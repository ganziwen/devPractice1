package com.example.autoframework.report.callback;

import cn.hutool.log.StaticLog;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.example.autoframework.alarm.service.AlarmService;
import com.example.autoframework.model.FailureInfo;
import com.example.autoframework.model.SummaryResult;
import com.example.autoframework.template.TemplateFacade;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.example.autoframework.enums.Project.DING_TALK_ROOT_URL;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DefaultReportCallback
 * @Description
 * @date 2021/12/5 16:49
 */
public class DefaultReportCallback implements ReportCallback {

    @Override
    public void postExecutionSummary(SummaryResult summaryResult) {
        StaticLog.info("测试报告默认的callback");

        HashMap<String, Object> reportTemplateMapping = Maps.newHashMap();
        reportTemplateMapping.put("testsFoundCount", summaryResult.getTestsFoundCount());
        reportTemplateMapping.put("testsSucceededCount", summaryResult.getTestsSucceededCount());
        reportTemplateMapping.put("testsFailedCount", summaryResult.getTestsFailedCount());
        reportTemplateMapping.put("testsSkippedCount", summaryResult.getTestsSkippedCount());
        reportTemplateMapping.put("timeStarted", summaryResult.getTimeStarted());
        reportTemplateMapping.put("timeFinished", summaryResult.getTimeFinished());

        reportTemplateMapping.put("failureReason", summaryResult.getFailureInfos().stream().map(failureInfo -> failureInfo.getThrowable().getMessage()).collect(Collectors.joining("\n* ")));

        // 模板数据替换
        String reportTemplate = TemplateFacade.replaceTemplate("default_report_template.md", reportTemplateMapping);

        OapiRobotSendResponse oapiRobotSendResponse = new AlarmService().sendDingTalkMarkDown(DING_TALK_ROOT_URL, summaryResult.getToken(), reportTemplate);
        // StaticLog.info(oapiRobotSendResponse.getBody());

    }

}
