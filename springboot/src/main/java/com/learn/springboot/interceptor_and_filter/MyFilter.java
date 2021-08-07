package com.learn.springboot.interceptor_and_filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MyFilter
 * @Description
 * @date 2021/8/7 16:53
 */
@Component
public class MyFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /**
     * 初始化时调用
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("---MyFilter.doFilter.start---");
        // 通过 servlet 可以获取 url 等信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getParameter("");
        // 继续调用下一个过滤器，没有则调用业务
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("---MyFilter.doFilter.end---");

    }

    /**
     * 销毁调用
     */
    @Override
    public void destroy() {

    }
}
