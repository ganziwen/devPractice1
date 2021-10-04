package service;

import common.model.SyncInstanceRequest;
import common.model.SyncInstanceResponse;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncService
 * @Description
 * @date 2021/10/3 11:22
 */
public interface SyncService {
    SyncInstanceResponse syncInstance(SyncInstanceRequest syncInstanceRequest);
}
