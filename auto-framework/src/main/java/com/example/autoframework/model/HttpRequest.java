package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HttpRequest
 * @Description
 * @date 2021/12/11 16:03
 */
@Data
@Builder
public class HttpRequest {
    private String url;
    private RequestType requestType;
    private ContentType contentType;
    private Map<String, String> headerMap;
    private Map<String, Object> params;
    private Object data;
}
