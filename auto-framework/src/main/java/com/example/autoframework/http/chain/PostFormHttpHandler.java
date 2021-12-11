package com.example.autoframework.http.chain;

import com.example.autoframework.model.ContentType;
import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.HttpResponse;
import com.example.autoframework.model.RequestType;
import com.google.common.collect.Maps;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PostFormHttpHandler
 * @Description
 * @date 2021/12/11 16:31
 */
public class PostFormHttpHandler extends AbstractHttpHandler {

    @Override
    protected boolean preRequest(HttpRequest request) {
        return request.getRequestType() == RequestType.POST && request.getContentType() == ContentType.FORM;
    }

    @Override
    protected Request createRequest(HttpRequest request) {
        FormBody.Builder builder = new FormBody.Builder();
        request.getParams().forEach((key, value) -> builder.add(key, String.valueOf(request.getParams())));
        RequestBody body = builder.build();

        return new Request.Builder()
                .url(request.getUrl())
                .post(body)
                .headers(Headers.of(Objects.isNull(request.getHeaderMap()) ? Maps.newHashMap() : request.getHeaderMap()))
                .build();
    }

}
