package com.development.mock.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockDataEntity
 * @Description 每个 mock 文件对应的对象
 * @date 2022/1/3 11:36
 */
@Data
public class MockDataEntity {
    private String mappingHost;
    private List<Map<String, Object>> mappingParams;
    private String response;
    private Long timeOut;
    private String pentrateUrl;
}
