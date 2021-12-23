package com.example.autoframework.model;

import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName NewOrder
 * @Description
 * @date 2021/12/23 12:17
 */
@Data
public class NewOrder {
    private NewUser payer;
    private NewUser payee;
    private String orderId;
    private Long amount;

}
