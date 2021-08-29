package com.example.mysqlschemasync.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncInstaceRequest
 * @Description
 * @date 2021/8/29 15:40
 */
@Data
public class SyncInstaceRequest {
    /**
     * 需要同步的源,需要校验ConnectInfo内的内容
     */
    @NotNull(message = "srcConnection can't be null")
    @Valid
    private ConnectInfo srcConnectInfo;
    /**
     * 目的
     */
    @NotNull(message = "dstConnectInfo can't be null")
    @Valid
    private ConnectInfo dstConnectInfo;
}
