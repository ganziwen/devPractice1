package com.development.mock.decorator;

import com.development.mock.model.HookContext;
import com.development.mock.model.MockContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CommHookDecorator
 * @Description
 * @date 2022/1/16 11:16
 */
public class CommHookDecorator extends BaseResponseDecorator<HookContext> {

    public static final Pattern PATTERN = Pattern.compile("(?<=\\$\\{hook:)(.*?)(?=\\})");

    public CommHookDecorator(BaseResponseDecorator<HookContext> innerDecorator) {
        super(innerDecorator);
    }


    private String genSearchElement(String hookFileName) {
        return String.format("${hook:%s}", hookFileName);
    }

    @Override
    protected HookContext onDecorate(HookContext hookContext) {
        /*
        这里处理返回数据：finalResponse
        实现思路：
        1. 要保证 mockContext 内带有请求的参数信息，比如其中就包含着 userId = xxx
        2. 我们设定好关于 hook 的匹配策略，${hook:userId} 基于正则来匹配，匹配{hook:xyz},进而拿到 userId 字段信息
        3. 拿到 userId 之后，去 mockContext 中的的 requestParam 中获取此字段的请求参数数值
        4. 做替换就完成
         */

        String finalResponse = hookContext.getFinalResponse();
        Map<String, String> requestParams = hookContext.getRequestParams();
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
        hookContext.setFinalResponse(finalResponse);
        return hookContext;
    }

}
