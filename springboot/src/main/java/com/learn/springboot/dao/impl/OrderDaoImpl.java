package com.learn.springboot.dao.impl;

import com.learn.springboot.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-16:23
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Override
    public Integer insertOrder(String orderId) {
        LOGGER.info("dao-insert into order sql: id = {}", orderId);
        return 1;
    }
}
