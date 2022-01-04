package com.auto.annotations;

import java.lang.annotation.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/4-10:47
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Depend {

    String rely() default "";

    String source() default "";

    String injection() default "";
} 
