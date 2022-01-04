package com.auto.annotations;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-18:00
 * 断言
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface AssertUtil {
    String sql() default "";

    String rule() default "";

    String status() default "";

    String key() default "";

    String value() default "";
}
