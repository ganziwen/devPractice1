package com.example.autoframework.annotation;

import com.example.autoframework.driver.DataDriverExtension;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DataDriver
 * @Description
 * @date 2021/12/12 10:22
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(DataDriverExtension.class)
@TestTemplate
public @interface DataDriver {
    // 测试用例文件路径，最好默认值就是当前包的当前方法名称
    String path() default "className";

}
