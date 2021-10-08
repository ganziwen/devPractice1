package com.example.mybatieswithspringboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-17:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbUserDO {
    private Long id;
    private String userId;
    private String userName;
}
