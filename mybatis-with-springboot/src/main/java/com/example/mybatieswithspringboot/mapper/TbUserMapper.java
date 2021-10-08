package com.example.mybatieswithspringboot.mapper;

import com.example.mybatieswithspringboot.bean.TbUserDO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-17:22
 */
@Repository
public interface TbUserMapper {

    /**
     * dao 这一层也不知道上一层也就是 controller 那一层要的数据是什么
     *
     * @return
     */
    @Select("select * from `tb_user`;")
    List<TbUserDO> selectTbuser();
}
