package com.example.autoframework.http.chain;

import com.alibaba.fastjson.JSON;
import com.example.autoframework.model.ContentType;
import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.RequestType;
import com.google.common.collect.Maps;
import okhttp3.Headers;
import okhttp3.MediaType;
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
public class PostXmlHttpHandler extends AbstractHttpHandler {


    @Override
    protected boolean preRequest(HttpRequest request) {
        return false;
    }

    @Override
    protected Request createRequest(HttpRequest request) {
        return null;
    }
}
