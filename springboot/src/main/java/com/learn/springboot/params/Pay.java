package com.learn.springboot.params;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/2-19:48
 */
@RestController
@RequestMapping("/pay")
public class Pay {
    private static final Logger LOGGER = LoggerFactory.getLogger(Pay.class);

    // 限定 URI 的值一定要有 value 值,也给了默认值
    @RequestMapping("payWithParams")
    public String pay(@RequestParam(value = "payId", required = true, defaultValue = "666") String payId) {
        LOGGER.info("pay start,payId:{}", payId);
        return String.format("%s-pay.success", payId);
    }

}
