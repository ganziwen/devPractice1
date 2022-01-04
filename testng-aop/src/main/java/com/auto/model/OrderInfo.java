package com.auto.model;

import lombok.Data;
import org.testng.annotations.DataProvider;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/4-10:57
 */
@Data
public class OrderInfo {
    private Long orderId;
    private String userName;
    private Long orderAmount;
    private boolean enableFlag;
}
