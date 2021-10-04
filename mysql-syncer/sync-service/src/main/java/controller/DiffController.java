package controller;

import common.model.DiffInstanceRequest;
import common.model.DiffInstanceResponse;
import common.model.RetMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DiffService;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DiffController
 * @Description
 * @date 2021/10/3 11:20
 */
@RestController
@RequestMapping("/diff")
public class DiffController {
    @Autowired
    private DiffService diffService;

    @RequestMapping("/instance")
    public RetMsg diffInstance(DiffInstanceRequest request) {
        DiffInstanceResponse diffInstanceResponse = diffService.diffInstance(request);
        return new RetMsg(200, "success", diffInstanceResponse);
    }
}
