package com.auto.annotations;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/30-18:46
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {
    String val() default "";
}
