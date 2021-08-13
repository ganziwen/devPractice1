package com.example.mybatieswithspringboot.controller;

import com.example.mybatieswithspringboot.bean.ResultMsg;
import com.example.mybatieswithspringboot.bean.TbUserDTO;
import com.example.mybatieswithspringboot.bean.TbUserDO;
import com.example.mybatieswithspringboot.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-18:05
 */
@RestController
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    /**
     * controller 这一层不知道实现字段具体是什么情况
     *
     * @return
     */
    @RequestMapping("selectTbUser")
    public ResultMsg<List<TbUserDTO>> selectTbUser() {
        List<TbUserDTO> tbUsers = tbUserService.selectTbUsers();
        return ResultMsg.success(tbUsers);
    }
}
