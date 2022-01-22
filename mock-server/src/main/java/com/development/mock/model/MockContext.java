package com.development.mock.model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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
    private Long timeOut;
    private String pentrateUrl;

    /*
    中断标志位，设置为 true 的时候代表直接退出不处理接下来的策略
     */
    private boolean breakFlag;

    public void setBreakFlag() {
        breakFlag = true;
    }


    public String getMockFileName() {
        // 将 uri 的第一个 / 去除掉，并且将所有的 / 替换成 _
        // return StringUtils.replace(StringUtils.substringAfter(this.requestUri, "/"), "/", "_");
        return StringUtils.replace(this.requestUri, "/", "\\");
        // return this.requestUri;
    }
}
