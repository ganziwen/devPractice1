package reflect;


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
    public void test1() {
        try {
            Class<?> aClass = Class.forName("reflect.User");
            Object newInstance = aClass.newInstance();
            System.out.println(aClass.getName().toString());
            Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
                System.out.println("field.getType().getName() = " + field.getType().getName());
                field.setAccessible(true);
                try {
                    field.set(newInstance, field.getType().getName().equals("java.lang.String") ? "testString" : 123);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            });
            System.out.println("newInstance.toString() = " + newInstance.toString());
            Method method = aClass.getDeclaredMethod("getMethodPrint");
            method.invoke(newInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
