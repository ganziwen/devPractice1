package com.example.autoframework.annotation;

import java.lang.annotation.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AutoTest
 * @Description
 * @date 2021/11/13 10:37
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPoints {
    CheckPoint[] value();
}
