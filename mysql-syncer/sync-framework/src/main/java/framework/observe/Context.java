package framework.observe;

import common.model.ConnectInfo;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Context
 * @Description
 * @date 2021/10/10 11:37
 */
@Data
public class Context {
    private ConnectInfo srcConnectInfo;
    private ConnectInfo dstConnectInfo;
}
