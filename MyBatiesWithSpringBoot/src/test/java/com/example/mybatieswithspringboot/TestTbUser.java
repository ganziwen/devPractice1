package com.example.mybatieswithspringboot;

import com.example.mybatieswithspringboot.bean.TbUserDO;
import com.example.mybatieswithspringboot.mapper.TbUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/13-17:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTbUser {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Test
    public void testTbUser1() {

        List<TbUserDO> tbUsers = tbUserMapper.selectTbuser();
        System.out.println("tbUsers = " + tbUsers);
    }
}
