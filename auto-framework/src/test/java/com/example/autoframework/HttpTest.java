package com.example.autoframework;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/6-12:21
 */
public class HttpTest {
    @Test
    public void testHttp() {
        //新建一个OkHttpClient对象
        OkHttpClient client = new OkHttpClient();

        String url = "http://www.baidu.com";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
