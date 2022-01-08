package com.development.mock.model;

import ch.qos.logback.core.util.SystemInfo;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockContext
 * @Description
 * @date 2022/1/2 11:55
 */
@Data
@Builder
public class MockContext {
    private String requestUri;
    private Map<String, String> requestParams;
    private String requestIp;
    private String mockFileName;

    private String finalResponse;
    private List<MockDataInfo> mockDataInfoList;

    public String getMockFileName() {
        // 将 uri 的第一个 / 去除掉，并且将所有的 / 替换成 _
        // return StringUtils.replace(StringUtils.substringAfter(this.requestUri, "/"), "/", "_");
        return StringUtils.replace(this.requestUri, "/", "\\");
        // return this.requestUri;
    }
}
