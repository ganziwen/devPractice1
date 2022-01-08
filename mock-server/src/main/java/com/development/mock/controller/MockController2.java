package com.development.mock.controller;

import com.development.mock.model.MockContext;
import com.development.mock.service.MockService;
import com.development.mock.util.ArrayUtils;
import com.development.mock.util.JsonFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockController
 * @Description 目前用的是文件的形式，其实按照我的理解，可以迁移到 redis 内，redis 也可以按层级来标识文件信息，而且更直观
 * @date 2021/12/26 15:56
 */
@RestController
public class MockController2 {

    @Resource
    private HttpServletRequest request;

    @Resource
    private MockService mockService;

    @RequestMapping("/**")
    public String doMock() {
        /*
        1. 拿到 uri 、拿到参数、拿到请求的 ip 地址
        2. 到指定的 mock 数据目录下查找匹配到的 mock 数据
            2.1 解析文件，然后按照参数和 ip 去进行匹配，并且每个匹配节点都有权重，默认 = 1
            2.2 计算权重，大的那个返回
         */
        MockContext mockContext = MockContext.builder()
                .requestIp(request.getRemoteHost())
                .requestUri(request.getRequestURI())
                .requestParams(parseRequestParam())
                .build();
        Logger.info("do mock start ,context = {}", JsonFactory.objectToJson(mockContext));

        return mockService.doMock(mockContext);

    }

    // 获取 get 方法的参数列表
    private Map<String, String> parseRequestParam() {
        return this.request.getParameterMap()
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> ArrayUtils.getFirstValue(entry.getValue())));
    }
}
