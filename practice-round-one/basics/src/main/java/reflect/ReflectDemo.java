package reflect;


import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ReflectDemo
 * @Description
 * @date 2022/2/27 20:56
 */
public class ReflectDemo {
    @Test
    public void test1() throws Exception {
        // 获取到反射的类
        Class<?> aClass = Class.forName("reflect.User");
        // 实例化出一个对象
        Object newInstance = aClass.newInstance();
        // 获取类的完整名字
        System.out.println("aClass.getName() = " + aClass.getName());

        // 获取类的 public 属性
        System.out.println("Arrays.toString(aClass.getFields()) = " + Arrays.toString(aClass.getFields()));
        // 获取类的所有属性
        System.out.println("Arrays.toString(aClass.getDeclaredFields()) = " + Arrays.toString(aClass.getDeclaredFields()));

        // 获取类的所有 public 方法
        System.out.println("Arrays.toString(aClass.getMethods()) = " + Arrays.toString(aClass.getMethods()));

        // 获取类的所有方法
        System.out.println("Arrays.toString(aClass.getDeclaredMethods()) = " + Arrays.toString(aClass.getDeclaredMethods()));
        // 获取到类的构造器
        System.out.println("Arrays.toString(aClass.getConstructors()) = " + Arrays.toString(aClass.getConstructors()));

        // 获取类的所有属性
        Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
            System.out.println("field.getType().getName() = " + field.getType().getName());
            field.setAccessible(true);
            try {
                // 对实例化的对象进行操作
                field.set(newInstance, field.getType().getName().equals("java.lang.String") ? "testString" : 123);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        System.out.println("newInstance.toString() = " + newInstance.toString());
        Method method = aClass.getDeclaredMethod("getMethodPrint");
        method.invoke(newInstance);


    }
}
