package com.example.mybatieswithspringboot.bean;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-18:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUserDTO {
    private String userId;
    private String userName;
}
