package com.example.autoframework.driver.model;

import lombok.Data;

import javax.validation.constraintvalidation.SupportedValidationTarget;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Order
 * @Description
 * @date 2021/12/22 12:28
 */
@Data
public class Order {
    private String id;
    private String orderId;
    private Long amount;
}
