package com.development.mock.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HookContext
 * @Description
 * @date 2022/1/16 11:37
 */
@Data
@Builder
public class HookContext {
    private Map<String, String> requestParams;
    private String finalResponse;
}
