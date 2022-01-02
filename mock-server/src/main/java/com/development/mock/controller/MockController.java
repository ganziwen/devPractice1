package com.development.mock.controller;

import cn.hutool.core.io.IoUtil;
import com.development.mock.model.MockContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockController
 * @Description
 * @date 2021/12/26 15:56
 */
@RestController
public class MockController {

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    // 这个地址是你自己电脑上的绝对路径
    private static final String MOCK_DATA_ROOT_PATH = "D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\";

    @Resource
    private HttpServletRequest request;

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
        logger.info("do mock start ,context = {}", String.valueOf(mockContext));

        /*
        基于 uri 获取目录下的指定文件，并判断其属性，是目录还是文件
            如果是文件，直接返回了，如果是目录则需要解析匹配
         */
        String filePath = MOCK_DATA_ROOT_PATH + mockContext.getMockFileName();
        File mockDataFile = new File(filePath);
        if (mockDataFile.isFile()) {
            logger.info("{} is file", filePath);
            try {
                return IoUtil.read(new FileInputStream(filePath), Charset.defaultCharset());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        return "none";
    }

    // 获取 get 方法的参数列表
    private Map<String, String> parseRequestParam() {
        return this.request.getParameterMap()
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> getFirstValue(entry.getValue())));
    }

    private String getFirstValue(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";

        }
        return strArr[0];
    }

}
