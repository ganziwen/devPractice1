package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FailureInfo
 * @Description
 * @date 2021/12/5 15:58
 */
@Data
@Builder
public class FailureInfo {
    private String className;
    private String methodName;
    private String parameterTypes;
    private Throwable throwable;
}
