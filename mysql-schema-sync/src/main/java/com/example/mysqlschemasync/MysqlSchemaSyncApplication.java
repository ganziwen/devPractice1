package com.example.mysqlschemasync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MysqlSchemaSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlSchemaSyncApplication.class, args);
    }

}
