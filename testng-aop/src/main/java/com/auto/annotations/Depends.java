package com.auto.annotations;

import java.lang.annotation.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/4-10:48
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Depends {
    Depend[] value();
}
