package com.example.autoframework.http.service;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HttpService
 * @Description 当然这里也可以直接用 hutool
 * @date 2021/12/4 20:04
 */
public class HttpService {

    /**
     * json 形式
     */
    public String doPostJson(String url, Map<String, String> headers, Object data) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json;chartset=utf-8");

        RequestBody requestBody = RequestBody.create(JSON.toJSONString(data), mediaType);
        // 请求组装
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Headers.of(Objects.isNull(headers) ? Maps.newHashMap() : headers))
                .build();
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

    public String doPostJson(String url, Object data) {
        return doPostJson(url, null, data);

    }

    /**
     * form 形式
     */
    public String doPostForm(String url, Map<String, String> headers, Map<String, Object> params) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach((key, value) -> builder.add(key, String.valueOf(value)));
        FormBody formBody = builder.build();
        // 请求组装
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .headers(Headers.of(Objects.isNull(headers) ? Maps.newHashMap() : headers))
                .build();
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

    public String doPostForm(String url, Map<String, Object> params) {
        return doPostForm(url, null, params);

    }
}
