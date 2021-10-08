package com.example.mybatieswithspringboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResultMsg<T> {
    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MESSAGE = "SUCCESS";


    private String code;
    private String msg;
    private T data;

    public ResultMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultMsg<T> success(T data) {
        return new ResultMsg<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> ResultMsg<T> error(String code, String message) {
        return new ResultMsg<>(code, message);
    }
}
