package com.learn.springboot.value_from_application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-16:49
 */
@Component
@ConfigurationProperties(prefix = "person")
@Data
public class Person {
    private String userName;
    private int intAge;
}
