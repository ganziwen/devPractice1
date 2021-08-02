package com.learn.springboot.mvc;

import com.learn.springboot.mvc.service.impl.PayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.rmi.server.UnicastRemoteObject;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-18:08
 */
@Configuration
// @ComponentScan(basePackages = {"com.learn.springboot.mvc.service.impl.**"})
public class Conf {
    @Bean
    public PayServiceImpl payService() {
        return new PayServiceImpl();
    }
}
