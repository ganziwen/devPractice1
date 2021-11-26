package com.example.autoframework.annotation;

import com.example.autoframework.engine.CaseEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CaseSelector
 * @Description case 的选择引擎
 * @date 2021/11/13 10:37
 */

// TODO: 2021/11/20 有时间研究一下看能不能直接聚合几个注解
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// @ExtendWith(CaseSelectExtension.class)
@ExtendWith(CaseEngineExtension.class)

// @CaseGroup
// @CaseTag(key = "", val = "")
@Test
public @interface CaseSelector {

    String scanPackage();

    String key() default "";

    String val() default "";

    // 其实只用 key 和 val 即可,这是新扩展的功能
    String team() default "";

    String group() default "";


}
