package com.learn.springboot.value_from_application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-17:27
 */
@Component
@Data
@ConfigurationProperties(prefix = "mysql")
@PropertySource("classpath:db.properties")
public class DataBase {
    private String url;
    private String userName;
    private String passWord;
}
