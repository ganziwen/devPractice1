package com.example.autoframework.http.service;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/10-18:53
 */
public class HttpServiceDesign {

    /**
     * 发送请求的方法
     *
     * @param request
     * @return
     */
    private String handleResponse(Request request) {
        OkHttpClient client = new OkHttpClient();
        // 构建请求
        Call call = client.newCall(request);
        try {
            // 真正执行发送，拿到 response
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            Object o = responseBody == null ? "返回的body为空" : responseBody;
            StaticLog.info(String.valueOf(o));
            return String.valueOf(o);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Request createRequest(String url, Map<String, String> headers, RequestBody body) {

        return new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(Objects.isNull(headers) ? Maps.newHashMap() : headers))
                .build();

    }

    /**
     * json 形式
     */
    public String doPostJson(String url, Map<String, String> headers, Object data) {
        MediaType mediaType = MediaType.parse("application/json;chartset=utf-8");

        RequestBody body = RequestBody.create(JSON.toJSONString(data), mediaType);
        Request request = createRequest(url, headers, body);
        return handleResponse(request);

    }

    public String doPostJson(String url, Object data) {
        return doPostJson(url, null, data);

    }

    /**
     * form 形式
     */
    public String doPostForm(String url, Map<String, String> headers, Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach((key, value) -> builder.add(key, String.valueOf(value)));
        RequestBody body = builder.build();
        Request request = createRequest(url, headers, body);
        return handleResponse(request);


    }

    public String doPostForm(String url, Map<String, Object> params) {
        return doPostForm(url, null, params);

    }

}
