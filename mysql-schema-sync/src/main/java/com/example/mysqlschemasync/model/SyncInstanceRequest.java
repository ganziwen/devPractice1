package com.example.mysqlschemasync.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncInstanceRequest
 * @Description
 * @date 2021/8/29 15:40
 */
@Data
public class SyncInstanceRequest {
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

    private List<String> excludeDbNames;

    /**
     * 可排除的库和表
     */
    private Map<String, List<String>> excludeDbAndTables;
}
