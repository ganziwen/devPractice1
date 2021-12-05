package com.example.autoframework.alarm.service;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.example.autoframework.annotation.CaseDesc;
import com.example.autoframework.annotation.CaseTitle;
import com.example.autoframework.annotation.CheckPoint;
import com.example.autoframework.annotation.CheckPoints;
import com.example.autoframework.engine.listener.FailureListener;
import com.example.autoframework.model.FailureResult;
import com.example.autoframework.template.TemplateFacade;
import com.example.autoframework.util.ReflectUtils;
import com.google.common.collect.Maps;
import com.taobao.api.ApiException;
import okhttp3.Response;
import sun.swing.plaf.synth.DefaultSynthStyle;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static com.example.autoframework.enums.Project.DING_DING_TOKEN;
import static com.example.autoframework.enums.Project.DING_TALK_ROOT_URL;

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

        // 基于 className 和 methodName 用反射获取到 java.lang.reflect.Method 这里拿到的反射是发生了错误的 case 的那个 method
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
            check = Arrays.stream(checkPoints).map(CheckPoint::value).collect(Collectors.joining(";"));

        }

        // 开始封装成个 map
        HashMap<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("title", title);
        resultMap.put("desc", desc);
        resultMap.put("owner", owner);
        resultMap.put("check", check);
        resultMap.put("caseId", caseId);
        resultMap.put("cause", cause);
        String alarmTemplateRes = TemplateFacade.replaceTemplate("default_alarm_template.md", resultMap);

        // 将此消息发出去
        // StaticLog.error(alarmTemplateRes);

        // TODO: 2021/12/5 还是有点问题，就是假设跑的是个 package，每次执行错误一个，就会发送请求出去，未免太烦了吧，
        //  可不可以直接判断本次所有的用例执行结果呢？但是假设是跑完再去发就跟发测试报告没什么大的区别的，所以这里的策略需要在考虑下，是不是应该跑到一定的重试次数再去发这个告警
        //  可以在测试类上加个注解，叫 @AlarmConfig(id = "testAlarm",num = 3)，可以去控制这个测试类里面 p0 级别的 case 错了几个，再来 用个 Map<string , AtomicInteger>控制叠加的次数，再发送告警，这是针对测试类的级别进行控制
        // 调用 dingtalk 的 sdk 方法
        OapiRobotSendResponse oapiRobotSendResponse = sendDingTalkMarkDown(DING_TALK_ROOT_URL, failureResult.getToken(), alarmTemplateRes);
        StaticLog.info(oapiRobotSendResponse.getBody());
    }

    /**
     * 钉钉的 sdk 提供的方式
     *
     * @param url
     * @param token
     * @param markdownText
     * @return
     */
    public OapiRobotSendResponse sendDingTalkMarkDown(String url, String token, String markdownText) {
        DingTalkClient client = new DefaultDingTalkClient(StrUtil.format("{}?access_token={}", url, token));
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("告警");
        markdown.setText(markdownText);
        request.setMarkdown(markdown);
        try {
            StaticLog.info("开始请求");
            return client.execute(request);
        } catch (ApiException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * http 发送的方式
     *
     * @param url
     * @param token
     * @param text
     * @return
     */
    private String sendDingTalkHttp(String url, String token, String text) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access_token", DING_DING_TOKEN);

        // markdown 的发送形式
        String postTemp = StrUtil.format("{\"msgtype\": \"markdown\",\"markdown\": {\"title\":\"告警\",\"text\":\"{}\"}}", text);
        // JSONObject jsonObject = JSON.parseObject(postTemp);
        StaticLog.info(DING_TALK_ROOT_URL + HttpUtil.toParams(paramMap));
        StaticLog.info(postTemp);

        String body = HttpRequest.post(DING_TALK_ROOT_URL + "?" + HttpUtil.toParams(paramMap)).body(postTemp).timeout(2000).execute().body();
        StaticLog.info(body);
        return body;

    }


}
