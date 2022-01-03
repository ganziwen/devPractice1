package com.development.mock.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MappingParamInfo
 * @Description
 * @date 2022/1/3 11:36
 */
@Data
public class MappingParamData {
    private String mappingHost;
    // private long weight;
    private List<Map<String, Object>> mappingParams;
    private String response;
}
