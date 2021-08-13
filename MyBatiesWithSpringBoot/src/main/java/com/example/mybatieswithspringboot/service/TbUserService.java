package com.example.mybatieswithspringboot.service;

import com.example.mybatieswithspringboot.bean.TbUserDTO;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-17:59
 */
public interface TbUserService {
    List<TbUserDTO> selectTbUsers();
}
