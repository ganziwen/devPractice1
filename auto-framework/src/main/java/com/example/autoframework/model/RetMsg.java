package com.example.autoframework.model;

import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RetMsg
 * @Description
 * @date 2021/12/23 12:19
 */
@Data
public class RetMsg<T> {
    private Integer errNo;
    private String errMsg;
    private T data;
}
