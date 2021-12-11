package com.example.autoframework.http.chain;

import cn.hutool.log.StaticLog;
import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.HttpResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AbstractHttpHandler
 * @Description
 * @date 2021/12/11 16:05
 */
public abstract class AbstractHttpHandler {

    private AbstractHttpHandler nextHandler;

    protected abstract boolean preRequest(HttpRequest request);

    protected abstract Request createRequest(HttpRequest request);


    private HttpResponse onRequest(HttpRequest request) {
        Request req = createRequest(request);
        return handleResponse(req);
    }


    public HttpResponse doRequest(HttpRequest request) {
        Objects.requireNonNull(request, "http request should not be null");

        if (preRequest(request)) {
            return onRequest(request);
        }
        // 如果不是当前链处理，下一个链不为空
        if (!(Objects.isNull(this.nextHandler))) {
            return this.nextHandler.onRequest(request);
        }
        // 所有的链都没执行到
        throw new IllegalStateException("unknow http request:" + request);
    }

    private HttpResponse handleResponse(Request request) {
        OkHttpClient client = new OkHttpClient();
        // 构建请求
        Call call = client.newCall(request);
        try {
            // 真正执行发送，拿到 response
            Response response = call.execute();
            if (!response.isSuccessful()) {
                throw new IllegalStateException("do http failed");
            }
            ResponseBody responseBody = response.body();

            if (!Objects.isNull(responseBody)) {
                // return responseBody.string();
                return HttpResponse.builder().body(responseBody.string()).build();
            } else {
                throw new IllegalStateException("get http response body failed");
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void setNextHandler(AbstractHttpHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
