package com.learn.springboot.service.impl;

import com.learn.springboot.dao.OrderDao;
import com.learn.springboot.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-16:13
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Override
    public Boolean createOrder(String orderId) {
        LOGGER.info("order service prepare create order for {}", orderId);
        Integer effectRows = orderDao.insertOrder(orderId);
        return effectRows >= 1;
    }
}
