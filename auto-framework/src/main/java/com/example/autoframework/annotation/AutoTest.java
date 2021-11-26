package com.example.autoframework.annotation;

import com.example.autoframework.engine.CaseFormatExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AutoTest
 * @Description
 * @date 2021/11/13 10:37
 */

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(CaseFormatExtension.class)
@Test
public @interface AutoTest {
}
