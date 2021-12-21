package com.example.autoframework.cases.driver;

import com.example.autoframework.annotation.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Parameter;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName testDriver
 * @Description
 * @date 2021/12/12 10:58
 */
public class testDriver {

    /**
     * 需要被测试的方法
     *
     * @param name
     * @param id
     * @return
     */
    public String call(String name, Integer id) {
        System.out.println(name);
        System.out.println(id);
        return name + String.valueOf(id);
    }


    /**
     * 正常做法：
     * 通过定义一个 data，csv 文件里面有这么几列：
     * name,id,resp
     * Tom,1,res1
     * Jim,2,res2
     * ……
     * <p>
     * 1. 解析数据文件
     * 2. 按照 csv 文件的列定义做赋值给 name,id
     * 3. 循环调用校验结果
     */
    @AutoTest
    @CheckPoint("xxx")
    @CaseTitle("老的做法")
    @CaseTag(key = "name", val = "val")
    @CaseDesc(desc = "老测试", owner = "qa")
    public void testNormal() {

        String name = null;
        Integer id = null;

        String res = call(name, id);
    }

    // @AutoTest
    // @CheckPoint("没检查点")
    // @CaseTitle("新的做法")
    // @CaseDesc(desc = "新测试", owner = "qa")
    // @CaseTag(key = "name", val = "val")
    // @DataDriver(path = "data/testDriver.yml")
    // public void testCallNew(String name, Integer id) {
    //     // 1. 去 @DataDriver 中找到 path 为对应的文件进行解析
    //     // 2. 解析资源文件，存为 List<T>
    //     // 3. 将 List 中的每个数据类型按照方法签名的参数名或者类型做赋值
    //     // 4. 实现循环调用，将值全部塞给方法
    //     String res = call(name, id);
    // }


    @AutoTest
    @CheckPoint("没检查点")
    @CaseTitle("新的做法")
    @CaseDesc(desc = "新测试", owner = "qa")
    @CaseTag(key = "name", val = "val")
    @DataDriver(path = "data/testDriver.yml")
    public void testCallNew2(@DataParam("name") String name, @DataParam("id") Integer id) {
        // 1. 去 @DataDriver 中找到 path 为对应的文件进行解析
        // 2. 解析资源文件，存为 List<T>
        // 3. 将 List 中的每个数据类型按照方法签名的参数名或者类型做赋值
        // 4. 实现循环调用，将值全部塞给方法
        String res = call(name, id);
        System.out.println("res = " + res);
    }





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
