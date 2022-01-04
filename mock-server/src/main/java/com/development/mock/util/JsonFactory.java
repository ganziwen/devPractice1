package com.development.mock.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.development.mock.model.MockContext;
import org.testng.annotations.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/4-20:03
 */
public class JsonFactory {
    /**
     * 将 obj 转成 json 美化输出
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJson(T obj) {
        return JSONArray.toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    @Test
    public void testObjectToJson() {
        MockContext context = MockContext.builder().requestUri("www.baidu.com").requestIp("127.0.0.1").build();
        System.out.println(objectToJson(context));
    }
}
