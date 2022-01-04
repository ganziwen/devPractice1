package com.auto.annotations;

import java.lang.annotation.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/31-18:02
 * 断言
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Assert {
    AssertUtil[] value();
}
