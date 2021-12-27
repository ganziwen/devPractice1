package com.example.autoframework.paramholder;

import com.example.autoframework.util.RequiredUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ParamHolder
 * @Description
 * @date 2021/12/23 12:25
 */
public interface ParamHolder {

    /**
     * mapping 是做类型名称映射的，比如由于命名冲突无法公用，此时换名后，需要做一个映射，使得 payer1 变成 payer
     *
     * @param clazz
     * @param maping
     * @param <T>
     * @return
     */
    default <T> T createBean(Class<T> clazz, Map<String, String> mapping) {
        /*
        可以拿到 class ，就能拿到class的字段名，比如说 NewOrder -> private String orderId
        由于 auto params holder 实现自此接口，则 this 也有这个字段：public String orderId
        orderId == orderId 所以就是 newClass ，然后将this 中不为 null 的值赋值过去就完事了
         */

        RequiredUtils.requiredNotNull(clazz, "should not be null");

        Field[] fields = clazz.getDeclaredFields();
        try {
            T instance = clazz.newInstance();
            for (Field field : fields) {
                // TODO: 2021/12/23 从mapping里找到 payer1 然后 get 时转成 payer
                Field holdField = this.getClass().getField(field.getName());
                if (!Objects.isNull(holdField.get(this))) {
                    field.setAccessible(true);
                    field.set(instance, holdField.get(this));
                }
            }
            return instance;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }


    }
}
