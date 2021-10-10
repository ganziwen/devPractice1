package framework.chain;

import common.model.ConnectInfo;
import framework.enums.DiffType;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncContext
 * @Description
 * @date 2021/10/10 16:08
 */
@Data
@Builder
public class SyncContext {
    private ConnectInfo srcConnectInfo;
    private ConnectInfo dstConnectInfo;
    private String database;

}
