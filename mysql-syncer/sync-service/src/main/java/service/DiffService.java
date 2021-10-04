package service;

import common.model.DiffInstanceRequest;
import common.model.DiffInstanceResponse;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffService
 * @Description
 * @date 2021/10/3 11:22
 */
public interface DiffService {
    DiffInstanceResponse diffInstance(DiffInstanceRequest diffInstanceRequest);
}
