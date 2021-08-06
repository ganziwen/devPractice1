package com.learn.springboot.bean;

import lombok.Data;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-10:47
 */
@Data
public class PayInfo {
    private String orderId;
    private String payId;
    private Integer amount;
}
