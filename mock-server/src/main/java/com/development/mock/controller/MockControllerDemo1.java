package com.development.mock.controller;

import cn.hutool.core.io.IoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockController
 * @Description
 * @date 2021/12/26 15:56
 */
@RestController
public class MockControllerDemo1 {

    private static final Logger logger = LoggerFactory.getLogger(MockControllerDemo1.class);

    // 这个地址是你自己电脑上的绝对路径
    private static final String MOCK_DATA_ROOT_PATH = "D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\";

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/demo1/**")
    public String doMock() {


        // 将 uri 的第一个 / 去除掉，并且将所有的 / 替换成 _
        String filePath = StringUtils.replace(StringUtils.substringAfter(request.getRequestURI(), "/"), "/", "_");
        logger.info("request.uri = {}", request.getRequestURI());
        try {
            // 将指定路径下的文件内容返回
            String data = IoUtil.read(new FileInputStream(new File(MOCK_DATA_ROOT_PATH + filePath)), Charset.defaultCharset());
            logger.info("request.response = {}", data);
            return data;
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
