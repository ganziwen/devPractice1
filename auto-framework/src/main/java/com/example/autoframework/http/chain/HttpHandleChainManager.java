package com.example.autoframework.http.chain;

import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.HttpResponse;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HttpHandleChainManager
 * @Description
 * @date 2021/12/11 17:16
 */
public final class HttpHandleChainManager {
    private final AbstractHttpHandler httpHandler;

    private HttpHandleChainManager() {
        this.httpHandler = initHandlerChain();
    }

    private AbstractHttpHandler initHandlerChain() {
        AbstractHttpHandler postFormHandler = new PostFormHttpHandler();
        AbstractHttpHandler postJsonHandler = new PostJsonHttpHandler();
        postFormHandler.setNextHandler(postJsonHandler);
        return postFormHandler;
    }

    public HttpResponse doRequest(HttpRequest request) {
        return this.httpHandler.doRequest(request);
    }

    private static class ClassHolder {
        private static final HttpHandleChainManager HOLDER = new HttpHandleChainManager();
    }

    public static HttpHandleChainManager of() {
        return ClassHolder.HOLDER;
    }

}
