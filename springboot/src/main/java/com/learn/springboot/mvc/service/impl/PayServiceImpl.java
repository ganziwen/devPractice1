package com.learn.springboot.mvc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-18:05
 * 加入 pay
 */
public class PayServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayServiceImpl.class);

    public Boolean payOrder(String orderId) {
        LOGGER.info("pay service prepare create pay for {}", orderId);

        return 1 > 0;
    }
}
