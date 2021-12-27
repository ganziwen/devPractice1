package com.example.autoframework.cases.api;


import com.example.autoframework.model.NewOrder;
import com.example.autoframework.model.RetMsg;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName OrderController
 * @Description
 * @date 2021/12/23 12:19
 */
public class OrderController {
    public RetMsg createOrder(NewOrder newOrder) {
        System.out.println("newOrder = " + newOrder);
        return new RetMsg();
    }
}
