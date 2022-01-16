package com.development.mock.observer;

import ch.qos.logback.core.joran.util.StringToObjectConverter;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.development.mock.model.MockContext;
import org.tinylog.Logger;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PenetrateObserver
 * @Description
 * @date 2022/1/16 17:41
 */
public class PenetrateObserver implements IObserver<MockContext> {
    @Override
    public void update(MockContext mockContext) {
        /*
        建设点：透传能力
        场景：部分请求不走 mock 拦截而是走真实的请求

        实现思路：
        1. 既然要做透传，即有一种请求，做完筛选之后，将其请求打向真实服务
        2. 需要在 mock 数据中做一个配置，即透传标志，配置上要请求的真实服务地址
        3. 确定走透传逻辑时，只需要将 mockcontext 中的 request param ，整合完成，就去请求被测服务即可
         */
        if (Objects.isNull(mockContext.getPentrateUrl())) {
            return;
        } else {
            Map<String, String> requestParams = mockContext.getRequestParams();
            String newUrl = mockContext.getPentrateUrl();
            Logger.info("request penetrate start,url = {},body =\n {}", newUrl, JSON.toJSONString(requestParams));
            // doHttp request 这里需要研究一下，发动出去的 requestParam 为空
            String body = HttpRequest.post(newUrl).header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded").form(String.valueOf(requestParams)).execute().body();
            mockContext.setFinalResponse(body);
        }
    }
}
