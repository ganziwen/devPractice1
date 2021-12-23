package com.example.autoframework.annotation;

import com.example.autoframework.profile.ProfileExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName EnvProfile
 * @Description 方法和类上都可以用
 * @date 2021/12/23 10:54
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({ProfileExtension.class})
public @interface EnvProfile {
    String value();
}
