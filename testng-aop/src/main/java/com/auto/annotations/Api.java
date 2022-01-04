package com.auto.annotations;

import java.lang.annotation.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-17:55
 * 接口文档
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Api {
    String address() default "";

    String method() default "";

    String url() default "";
}
