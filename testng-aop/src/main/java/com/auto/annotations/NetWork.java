package com.auto.annotations;

import java.lang.annotation.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-18:05
 * 异常的测试和探索性测试
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NetWork {

    String packets() default "";

    String delayed() default "";


}
