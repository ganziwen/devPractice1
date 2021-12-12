package com.example.autoframework.annotation;

import java.lang.annotation.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CheckPoint
 * @Description
 * @date 2021/11/13 13:33
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = CheckPoints.class)
public @interface CheckPoint {
    String value();
}
