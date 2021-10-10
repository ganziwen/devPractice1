package framework.model;

import common.model.ConnectInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncDatabaseData
 * @Description
 * @date 2021/10/10 17:02
 */
@Data
@Builder
public class SyncDatabaseData {
    private ConnectInfo connectInfo;
    private String database;
    private List<String> sqlList;
}
