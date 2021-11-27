package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FailureResult
 * @Description 用例失败的报告对象
 * @date 2021/11/27 14:25
 */
@Data
@Builder
public class FailureResult {
    private String className;
    private String methodName;
    private String parameterTypes;
    private Throwable throwable;
    private String token;
}
