package com.learn.springboot.controller;

import com.learn.springboot.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-16:10
 * MVC 的简单例子 - OrderController
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public String createOrder(String orderId) {
        LOGGER.info("create order start,orderId:{}", orderId);
        Boolean isCreateOrder = orderService.createOrder(orderId);
        return isCreateOrder ? "success" : "failed";
    }
}
