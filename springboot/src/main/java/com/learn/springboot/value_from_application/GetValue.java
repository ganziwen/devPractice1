package com.learn.springboot.value_from_application;

import com.learn.springboot.bean.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-14:55
 */
@RestController
public class GetValue {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetValue.class);

    @Value("${user.userName}")
    private String value;

    @RequestMapping("get_value")
    public RetMsg<String> getValueFromApplication() {


        LOGGER.info("getVlaueFromApplicatin start,get_value:{}", value);
        return RetMsg.buildSuccessMsg(value);

    }
}
