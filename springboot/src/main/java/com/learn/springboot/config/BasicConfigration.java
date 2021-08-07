package com.learn.springboot.config;

import com.learn.springboot.interceptor_and_filter.LoginInterceptor;
import com.learn.springboot.interceptor_and_filter.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName BasicConfigration
 * @Description 拦截器注入
 * @date 2021/8/7 15:02
 */

@Configuration
@ServletComponentScan(basePackages = "com.learn.servlet")
public class BasicConfigration implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    // @Autowired
    // MyFilter filter;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);
        interceptorRegistration.addPathPatterns("/**");
    }


    @Bean
    public FilterRegistrationBean<MyFilter> bascicFilter() {
        return new FilterRegistrationBean<>(new MyFilter());
    }
}
