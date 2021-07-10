package reflect;

import java.lang.reflect.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo1
 * @Description
 * @date 2021/7/10 12:37
 */
public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        testMethod();
//        testField();
//        testConstructor();
    }

    /**
     * 获取方法
     *
     * @throws ClassNotFoundException
     */
    public static void testMethod() throws ClassNotFoundException {
        // 取到整个类
        // Class<User> userClass = User.class;

        // 根据包名来获取到类,常用的一般使用此方式
        Class<?> userClass = Class.forName("reflect.User");

        // 获取到所有的方法包括 Object 内的方法,方法都是 Method 这个类的实现;但是获取不到私有方法
        // Method[] methods = userClass.getMethods();

        // 获取到定义的方法
        Method[] methods = userClass.getDeclaredMethods();

        // 根据方法名以及参数列表获取指定方法
        // Method foo1 = userClass.getMethod("foo1");
        for (Method method : methods) {
            // 获取方法名
            System.out.println("method.getName() = " + method.getName());

            // 获取每个方法的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for (Class<?> exceptionType : exceptionTypes) {
                System.out.println("exceptionType = " + exceptionType);
            }

            // 描述方法的权限(private,public,protected,static,final)
            int modifiers = method.getModifiers();
            boolean aPublic = Modifier.isPublic(modifiers);
            boolean aFinal = Modifier.isFinal(modifiers);

            // 获取参数个数
            int parameterCount = method.getParameterCount();
            System.out.println("parameterCount = " + parameterCount);
            System.out.println("\n=============\n");
        }
    }

    /**
     * 获取属性
     *
     * @throws ClassNotFoundException
     */
    public static void testField() throws ClassNotFoundException {
        Class<?> userClass = Class.forName("reflect.User");
        Field[] fields = userClass.getDeclaredFields();
        for (Field field : fields) {
            // 获取变量名称
            System.out.println("field.getName() = " + field.getName());
            // 获取变量的数据类型
            System.out.println("field.getType().getName() = " + field.getType().getName());

            // 还有其他的方法,可以自行调用看看
        }
    }

    /**
     * 获取构造器
     *
     * @throws ClassNotFoundException
     */
    public static void testConstructor() throws ClassNotFoundException {

        Class<?> aClass = Class.forName("reflect.User");
//        Class<User> userClass = User.class;
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor.getName() = " + constructor.getName());

            System.out.println("parameterTypeList:");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Parameter[] parameters = constructor.getParameters();

            for (Class<?> parameterType : parameterTypes) {
                String typeName = parameterType.getTypeName();
                System.out.println(typeName);
            }

            System.out.println("\n========\n");
        }

    }
}
