package com.development.mock.observer;

import com.development.mock.model.MockContext;
import com.development.mock.util.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.events.Event;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HookResponseObserver
 * @Description
 * @date 2022/1/16 10:08
 */
public class HookResponseObserver implements IObserver<MockContext> {

    public static final Pattern PATTERN = Pattern.compile("(?<=\\$\\{hook:)(.*?)(?=\\})");

    @Override
    public void update(MockContext mockContext) {
        String finalResponse = mockContext.getFinalResponse();

        /*
        这里处理返回数据：finalResponse
        实现思路：
        1. 要保证 mockContext 内带有请求的参数信息，比如其中就包含着 userId = xxx
        2. 我们设定好关于 hook 的匹配策略，${hook:userId} 基于正则来匹配，匹配{hook:xyz},进而拿到 userId 字段信息
        3. 拿到 userId 之后，去 mockContext 中的的 requestParam 中获取此字段的请求参数数值
        4. 做替换就完成
         */
        Map<String, String> requestParams = mockContext.getRequestParams();
        Matcher matcher = PATTERN.matcher(finalResponse);
        // 这里是一个循环，即如果你要匹配多个就用 while ，如果你的策略就匹配一个，那就用 if
        while (matcher.find()) {
            String hookFiledName = matcher.group(0);
            String searchElement = genSearchElement(hookFiledName);
            String hookFileValue = requestParams.get(hookFiledName);
            // {"errNo":500,"errMsg":"不确定","data":{"randomId":062572,"randomStr":"aILQlZ","hookStr":"${hook:userId}"}}
            // ${hook:userId}
            // 123456
            finalResponse = StringUtils.replace(finalResponse, searchElement, hookFileValue);

        }
        mockContext.setFinalResponse(finalResponse);
    }

    private String genSearchElement(String hookFileName) {
        return String.format("${hook:%s}", hookFileName);
    }

    @Test
    public void testReplace() {
        String finalResponse = "{\"errNo\":500,\"errMsg\":\"不确定\",\"data\":{\"randomId\":062572,\"randomStr\":\"aILQlZ\",\"hookStr\":\"${hook:userId}\"}}";
        String searchElement = "${hook:userId}";
        String hookFileValue = "123456";
        String replace = StringUtils.replace(finalResponse, searchElement, hookFileValue);
        System.out.println(replace);

    }
}
