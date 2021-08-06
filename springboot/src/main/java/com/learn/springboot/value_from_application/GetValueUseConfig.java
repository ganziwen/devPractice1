package com.learn.springboot.value_from_application;

import com.learn.springboot.bean.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/6-16:49
 */
public class GetValueUseConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetValue.class);

    @Autowired
    private Person person;

    @RequestMapping("get_value_config")
    public RetMsg<String> getValueFromApplicationConf() {


        LOGGER.info("getVlaueFromApplicatin start,get_value:{}", person);
        return RetMsg.buildSuccessMsg(person.toString());

    }

}
