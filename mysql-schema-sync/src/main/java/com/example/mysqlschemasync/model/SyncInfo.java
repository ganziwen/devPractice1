package com.example.mysqlschemasync.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncInfo
 * @Description 同步的内容
 * @date 2021/8/29 11:00
 */
@Data
public class SyncInfo {
    /**
     * 需要同步的源,需要校验ConnectInfo内的内容
     */
    @NotNull(message = "Sync.srcConnection can't be null")
    @Valid
    private ConnectInfo srcConnectInfo;
    /**
     * 目的
     */
    @NotNull(message = "Sync.dstConnectInfo can't be null")
    @Valid
    private ConnectInfo dstConnectInfo;

    private String dbName;
    private String tableName;

}
