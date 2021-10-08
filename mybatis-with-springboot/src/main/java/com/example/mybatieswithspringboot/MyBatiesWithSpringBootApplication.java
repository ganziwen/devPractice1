package com.example.mybatieswithspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.mybatieswithspringboot.mapper")
public class MyBatiesWithSpringBootApplication {
    /**
     * 注释
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MyBatiesWithSpringBootApplication.class, args);
    }

}
