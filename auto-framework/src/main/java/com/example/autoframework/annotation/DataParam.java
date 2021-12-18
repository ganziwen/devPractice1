package com.example.autoframework.annotation;

import java.lang.annotation.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DataParam
 * @Description
 * @date 2021/12/18 16:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface DataParam {
    String value();
}
