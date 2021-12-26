package com.development.mock.controller;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.IOUtils;

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
public class MockController {

    private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    private static final String MOCK_DATA_ROOT_PATH = "D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\";

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/**")
    public String doMock() {


        logger.info("request.uri = {}", request.getRequestURI());
        // 将 uri 的第一个 / 去除掉，并且将所有的 / 替换成 _
        String filePath = StringUtils.replace(StringUtils.substringAfterLast(request.getRequestURI(), "/"), "/", "_");
        try {
            // 将指定路径下的文件内容返回
            return IoUtil.read(new FileInputStream(new File(MOCK_DATA_ROOT_PATH + filePath)), Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
