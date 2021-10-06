package framework.chain;

import common.model.ConnectInfo;
import framework.enums.DiffType;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffContext
 * @Description
 * @date 2021/10/6 14:06
 */
@Data
public class DiffContext {
    DiffType diffType;
    private ConnectInfo srcConnectInfo;
    private ConnectInfo dstConnectInfo;
}
