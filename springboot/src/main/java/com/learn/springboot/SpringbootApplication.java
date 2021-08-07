package com.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

// 关键注解,表示当前类是工程的启动类且会基于此类找到包,作为扫描的根
@SpringBootApplication
@ServletComponentScan(basePackages = "com.learn.servlet")
public class SpringbootApplication {

    /**
     * args 是启动参数
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
