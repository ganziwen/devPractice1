package http;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/6-11:36
 * 获取客户端的 http 请求的一些信息，这里是解析一些请求的参数
 */
public class GetHttpParams {


    /**
     * 获取请求所有参数返回个 map，post 请求的 form 形式
     *
     * @param request
     * @return
     */
    private Map<String, String> getAllPostFormRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
            }
        }
        return res;
    }

    /**
     * 获取 get 方法的参数列表，http://xxx.xxx.xxx:9999/aaa/bbb/ccc?a=1&b=2&c=3
     * 将其组装成 map：键为 a,b,c, 值分别为 1,2,3
     *
     * @param request
     * @return
     */
    private Map<String, String> getAllGetRequestParams(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<>();
        if (null != request.getQueryString()) {
            Arrays.stream(request.getQueryString().split("&")).map(entry -> {
                String key = entry.split("=")[0];
                String value = entry.split("=")[1];
                return res.put(key, value);
            }).collect(Collectors.toList());
        }

        return res;
    }

}
