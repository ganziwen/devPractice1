package com.example.autoframework.annotation;

import com.example.autoframework.model.ReportType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ReportConfig
 * @Description
 * @date 2021/12/5 11:28
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportConfig {
    ReportType type() default ReportType.DING_TALK;
}
