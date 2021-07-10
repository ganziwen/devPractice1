package reflect;

import javax.sound.midi.VoiceStatus;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName generateCode
 * @Description
 * @date 2021/7/10 16:57
 */
public class generateCode {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        testUser2();
    }

    public static void testUser() {
        User user = new User();
        user.setAge("18");
        user.setName("zhangsan");
        user.setSex("男");
        user.foo1("zhangsan", "18", "");
        //……
    }

    public static void testUser2() throws ClassNotFoundException, NoSuchMethodException {
        // 传入接口名和方法名
        String serviceName = "reflect.User#foo1";
        // 1.以 # 拆分,arr[0] = reflect.User,arr[1] = foo1
        // 2.用 arr[0] = reflect.User 找到类对象,利用 Class.forName
        Class<?> aClass = Class.forName("reflect.User");
        // 3.基于 aClass 找方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if ("foo1".equals(method.getName())) {
                Class<?>[] types = method.getParameterTypes();
                Class<?> type1 = types[0];

                Field[] fields = type1.getDeclaredFields();
                for (Field field : fields) {
                    System.out.println("field.getName() = " + field.getName());
                }
//                for (Class<?> type : types) {
//                    // 4. 拿到方法的属性,生成代码
//                    System.out.println("type = " + type);
//                    Field[] fields = type.getDeclaredFields();
//                    for (Field field : fields) {
//                        System.out.println("field.getName() = " + field.getName());
//                    }
//                }
            }
        }

    }
}
