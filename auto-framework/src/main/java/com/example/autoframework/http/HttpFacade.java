package com.example.autoframework.http;

import com.example.autoframework.http.chain.HttpHandleChainManager;
import com.example.autoframework.model.ContentType;
import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.HttpResponse;
import org.junit.jupiter.api.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HttpFacade
 * @Description 建议直接使用 hutool：https://www.javajike.com/book/hutool/chapter11/bdc0e4a73b84e402e98fcd94be58869d.html
 * webhook ：https://oapi.dingtalk.com/robot/send?access_token=6e33cfd5c739272b92e10a0e558370e2142d5a4dd68215e913393dcbed4397f3
 * @date 2021/12/4 20:03
 */
public final class HttpFacade {
    public HttpFacade() {

    }

    public static HttpResponse doRequest(HttpRequest request) {
        return HttpHandleChainManager.of().doRequest(request);
    }

    @Test
    public void testHttpFacade() {
        HttpFacade.doRequest(HttpRequest.builder()
                .url("")
                .contentType(ContentType.JSON)
                .data("")
                .build());
    }
}
