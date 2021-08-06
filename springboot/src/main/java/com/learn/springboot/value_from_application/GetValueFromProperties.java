package com.learn.springboot.value_from_application;

import com.learn.springboot.bean.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-17:27
 */
@RestController
public class GetValueFromProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetValueFromProperties.class);

    @Autowired
    private DataBase dataBase;

    @RequestMapping("get_value_from_properties")
    public RetMsg<String> getValueFromProperties() {
        LOGGER.info("getVlaueFromApplicatin start,get_value:{}", dataBase);
        return RetMsg.buildSuccessMsg(dataBase.toString());
    }

}
