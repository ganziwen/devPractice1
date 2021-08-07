package com.learn.servlet;


import org.apache.catalina.util.ParameterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName BasicServlet
 * @Description servlet
 * @date 2021/8/7 11:13
 */
// @WebServlet(name = "basicServlet", urlPatterns = "/*")
public class BasicServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicServlet.class);

    /**
     * 请求过来,通过这里进行处理
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 拿到请求的 URI 以及 参数
        String requestUri = req.getRequestURI();
        Map<String, String> paramMap = handleParamMap(req.getParameterMap());
        LOGGER.info("basic servlet start for uri={},params = {}", requestUri, paramMap);

        // 当 URI 匹配到了,就执行以下逻辑
        if (requestUri.equals("/ping_servlet")) {
            LOGGER.info("URL match");
            resp.setStatus(200);
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write("This is basic servlet".getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }

    private Map<String, String> handleParamMap(Map<String, String[]> parameterMap) {
        return parameterMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, param -> param.getValue()[0]));
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
