package utils;

import java.lang.reflect.Field;

/**
 * @author steven01.gan
 * @version 1.0
 */
public class InvokedUtils {
    public static Object getPrivateField(Object instance, String filedName) throws NoSuchFieldException, IllegalAccessException {
        Field field = instance.getClass().getDeclaredField(filedName);
        field.setAccessible(true);
        return field.get(instance);
    }
}
