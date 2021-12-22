package com.example.autoframework.cases.driver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName testDriverSimple
 * @Description
 * @date 2021/12/22 13:04
 */
public class testDriverSimple {
    /**
     * 最简单粗暴的例子
     *
     * @param name
     */
    @Test
    @ExtendWith(test111.class)
    public void test111(String name) {
        System.out.println("name = " + name);
    }

    static class test111 implements ParameterResolver {
        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return true;
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            Parameter parameter = parameterContext.getParameter();
            System.out.println("parameter = " + parameter);
            return "hello";
        }
    }
}
