package com.example.mysqlschemasync.controller;

import com.example.mysqlschemasync.model.RetMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncConrtoller
 * @Description
 * @date 2021/8/22 17:39
 */
@RestController
public class SyncConrtoller {

    private static final Logger logger = LoggerFactory.getLogger(SyncConrtoller.class);

    /**
     * 同步接口
     *
     * @return
     */
    @RequestMapping("sync")
    public RetMsg<String> doSync() {
        logger.info("sync start");
        return RetMsg.buildSuccessMsg("test");
    }
}
