package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 测试类
 *
 * @author Ganziwen
 * @version 1.0
 * @ClassName ServiceApp
 * @Description
 * @date 2021/7/10 17:28
 */
public class ServiceApp {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
//        User2 user2 = new User2();
//        user2.setName1("1");
//        user2.setName2("2");
//        user2.setName3("3");
//        //……
//        new Service().createUser(user2);
        testUser2();
    }

    public static void testUser2() throws ClassNotFoundException, NoSuchMethodException {
        // 传入接口名和方法名
        String serviceName = "reflect.Service#createUser";
        // 1.以 # 拆分,arr[0] = reflect.User,arr[1] = foo1
        // 2.用 arr[0] = reflect.User 找到类对象,利用 Class.forName
        Class<?> aClass = Class.forName("reflect.Service");
        // 3.基于 aClass 找方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if ("createUser".equals(method.getName())) {
                Class<?>[] types = method.getParameterTypes();
                Class<?> type1 = types[0];
                System.out.println("type1 = " + type1);
                Field[] fields = type1.getDeclaredFields();
                for (Field field : fields) {
                    System.out.println("createUser.getName() = " + field.getName());
                    // 接下来将 Name1 转成 user2.setName1("默认值"); 即可;其实就剩下字符串拼接了
                }
            }
        }

    }
}
