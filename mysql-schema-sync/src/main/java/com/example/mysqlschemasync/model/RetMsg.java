package com.example.mysqlschemasync.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-10:06
 * 简单的 http 请求结构体,实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetMsg<T> {

    private static final int SUCCESS_CODE = 200;
    private static final int ERR_CODE = 500;
    private static final String SUCCESS_MSG = "success";
    private static final String ERR_MSG = "fail";

    private Integer code;
    private String message;
    /**
     * 正常来讲, data 应该是泛型,接收任意一种数据类型
     */
    private T data;

    public static <T> RetMsg<T> buildSuccessMsg(T data) {
        return new RetMsg<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> RetMsg<T> buildFailMsg(T data) {
        return new RetMsg<>(ERR_CODE, ERR_MSG, data);
    }
}
