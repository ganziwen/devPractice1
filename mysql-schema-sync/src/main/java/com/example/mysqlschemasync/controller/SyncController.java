package com.example.mysqlschemasync.controller;

import com.example.mysqlschemasync.model.*;
import com.example.mysqlschemasync.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncConrtoller
 * @Description
 * @date 2021/8/22 17:39
 */
@RestController
@RequestMapping("sync")
public class SyncController {

    private static final Logger logger = LoggerFactory.getLogger(SyncController.class);

    @Autowired
    private SyncService syncService;

    /**
     * 同步的多态方式
     *
     * @param syncInfo
     * @return
     */
    @RequestMapping(value = "sync", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RetMsg<String> doSync(@Valid @RequestBody SyncInfo syncInfo) {
        logger.info("do sync start. syncInfo = {}", syncInfo);

        try {
            // 处理业务
            syncService.doSync(syncInfo);
            return RetMsg.buildSuccessMsg("test do sync success");
        } catch (Exception e) {
            logger.error("do Sync failed.", e);
            return RetMsg.buildFailMsg(e.getMessage());
        }


    }

    /**
     * 同步实例
     *
     * @param syncInstaceRequest
     * @return
     */
    @RequestMapping(value = "instance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RetMsg<String> doSyncInstance(@Valid @RequestBody SyncInstaceRequest syncInstaceRequest) {
        logger.info("do sync start. syncInfo = {}", syncInstaceRequest);

        try {
            // 处理业务
            syncService.doSyncInstance(syncInstaceRequest);
            return RetMsg.buildSuccessMsg("test doSyncInstance success");
        } catch (Exception e) {
            logger.error("do Sync failed.", e);
            return RetMsg.buildFailMsg(e.getMessage());
        }


    }

    /**
     * 同步库
     *
     * @param syncDatabaseRequest
     * @return
     */
    @RequestMapping(value = "database", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RetMsg<String> doSyncDatabase(@Valid @RequestBody SyncDatabaseRequest syncDatabaseRequest) {
        logger.info("do sync start. syncInfo = {}", syncDatabaseRequest);

        try {
            // 处理业务
            syncService.doSyncDatabase(syncDatabaseRequest);
            return RetMsg.buildSuccessMsg("test doSyncDatabase success");
        } catch (Exception e) {
            logger.error("do Sync failed.", e);
            return RetMsg.buildFailMsg(e.getMessage());
        }


    }


    /**
     * 同步表
     *
     * @param syncTableRequest
     * @return
     */
    @RequestMapping(value = "table", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RetMsg<String> doSyncTable(@Valid @RequestBody SyncTableRequest syncTableRequest) {
        logger.info("do sync start. syncInfo = {}", syncTableRequest);

        try {
            // 处理业务
            syncService.doSyncTable(syncTableRequest);
            return RetMsg.buildSuccessMsg("test doSyncTable success");
        } catch (Exception e) {
            logger.error("do Sync failed.", e);
            return RetMsg.buildFailMsg(e.getMessage());
        }
    }
}
