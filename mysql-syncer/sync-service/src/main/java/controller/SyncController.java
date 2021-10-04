package controller;

import common.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DiffService;
import service.SyncService;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Synctroller
 * @Description
 * @date 2021/10/3 11:21
 */
@RestController
@RequestMapping("/sync")
public class SyncController {
    @Autowired
    private SyncService syncService;

    @RequestMapping("/instance")
    public RetMsg diffInstance(SyncInstanceRequest request) {
        SyncInstanceResponse syncInstanceResponse = syncService.syncInstance(request);
        return new RetMsg(200, "success", syncInstanceResponse);
    }

}
