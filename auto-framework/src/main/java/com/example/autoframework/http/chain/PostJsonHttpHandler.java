package com.example.autoframework.http.chain;

import com.alibaba.fastjson.JSON;
import com.example.autoframework.model.ContentType;
import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.HttpResponse;
import com.example.autoframework.model.RequestType;
import com.google.common.collect.Maps;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.omg.PortableInterceptor.RequestInfo;

import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PostFormHttpHandler
 * @Description
 * @date 2021/12/11 16:31
 */
public class PostJsonHttpHandler extends AbstractHttpHandler {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json;chartset=utf-8");

    @Override
    protected boolean preRequest(HttpRequest request) {
        return request.getRequestType() == RequestType.POST && request.getContentType() == ContentType.JSON;
    }

    @Override
    protected Request createRequest(HttpRequest request) {

        RequestBody body = RequestBody.create(JSON.toJSONString(request.getData()), MEDIA_TYPE);
        return new Request.Builder()
                .url(request.getUrl())
                .post(body)
                .headers(Headers.of(Objects.isNull(request.getHeaderMap()) ? Maps.newHashMap() : request.getHeaderMap()))
                .build();
    }

}
