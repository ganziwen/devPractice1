package common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffInstanceRequest
 * @Description
 * @date 2021/10/3 11:41
 */
@Data
@AllArgsConstructor
public class DiffInstanceRequest {
    private ConnectInfo src;
    private ConnectInfo dst;
}

