package com.example.mybatieswithspringboot.service.impl;

import com.example.mybatieswithspringboot.bean.TbUserDTO;
import com.example.mybatieswithspringboot.bean.TbUserDO;
import com.example.mybatieswithspringboot.mapper.TbUserMapper;
import com.example.mybatieswithspringboot.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-17:59
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 在 service 这一层,将 do 转化成 dto.
     * 在实际的开发过程中，比如说数据库内会存一些数字为 1,2,3 等标记,就可以在这一层做转化,可行的方式就是在 dto 设置一些枚举;
     * 或者有的情况下,请求的参数会冗余,所以转换情况可能是一个 do 转成多个 dto ,也有可能是多个 do 转成一个 dto
     * 比如下面的情况,可以发现我们是没有返回 id 字段的
     *
     * @return
     */
    @Override
    public List<TbUserDTO> selectTbUsers() {
        List<TbUserDO> tbusers = tbUserMapper.selectTbuser();
        return tbusers.stream().map(tbUserDO -> new TbUserDTO(tbUserDO.getUserId(), tbUserDO.getUserName())).collect(Collectors.toList());
    }
}
