package com.example.autoframework.http.service;

import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import okhttp3.Request.Builder;

import java.io.IOException;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HttpService
 * @Description 当然这里也可以直接用 hutool
 * @date 2021/12/4 20:04
 */
public class HttpService {
    /**
     * form 形式
     */
    public String doPost(String url, Map<String, String> headers, Map<String, ?> params) {
        return null;
    }


    /**
     * json 形式
     */
    public String doPost(String url, Object data) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON.toJSONString(data), MediaType.get("application/json"));
        // 请求组装
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        // 构建请求
        Call call = client.newCall(request);
        try {
            // 真正执行发送，拿到 response
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            StaticLog.info(responseBody.string());
            return responseBody.string();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * json 形式
     */
    public String doPost(String url, Map<String, String> headers, Object data) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON.toJSONString(data), MediaType.get("application/json"));
        // 请求组装
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .post(requestBody)
                .build();
        // 构建请求
        Call call = client.newCall(request);
        try {
            // 真正执行发送，拿到 response
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            StaticLog.info(responseBody.string());
            return responseBody.string();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }
}
