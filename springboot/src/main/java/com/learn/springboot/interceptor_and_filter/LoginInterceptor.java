package com.learn.springboot.interceptor_and_filter;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName LoginInterceptor
 * @Description 拦截请求处理一些通用的逻辑, 比如登录
 * @date 2021/8/7 14:52
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    /**
     * 前置处理
     * 取 header 的 token ,存在再往下处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("---LoginInterceptor.preHandle---");

        String token = request.getHeader("token");
        logger.info("token={}", token);

        // 这个判断条件一般是要去 passport 看是否存在,我们这里就简写
        if (Strings.isNullOrEmpty(token)) {
            logger.error("token is null or empty");
            return false;

        } else {
            return true;
        }
    }

    /**
     * 后置处理
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("---LoginInterceptor.postHandle---");
    }

    /**
     * 处理完成
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("---LoginInterceptor.afterCompletion---");
    }
}
