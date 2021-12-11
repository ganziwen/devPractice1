package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HttpResponse
 * @Description
 * @date 2021/12/11 16:04
 */
@Data
@Builder
public class HttpResponse {
    String body;
}
