package com.learn.springboot.mvc.controller;

import com.learn.springboot.mvc.service.OrderService;
import com.learn.springboot.mvc.service.impl.PayServiceImpl;
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

    @Autowired
    private PayServiceImpl payService;

    /**
     * controller 层做异常处理
     *
     * @param orderId
     * @return
     */
    @RequestMapping("/create")
    public String createOrder(String orderId) {
        LOGGER.info("create order start,orderId:{}", orderId);

        try {

            Boolean isCreateOrder = orderService.createOrder(orderId);
            Boolean isPayOrder = payService.payOrder(orderId);
            return isCreateOrder ? "success" : "failed";
        } catch (Exception e) {
            LOGGER.error("occur error.", e);
            return "error = " + e.getMessage();
        }

    }
}
