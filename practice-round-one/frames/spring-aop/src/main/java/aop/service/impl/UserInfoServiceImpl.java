package aop.service.impl;

import aop.dao.UserDao;
import aop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName UserInfoServiceImpl
 * @Description
 * @date 2022/3/13 16:01
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserDao userDao;


    @Override
    public void serviceInfo(String name, int age) {
        System.out.println("UserInfoServiceImpl.serviceInfo");
        userDao.daoInfo(name, age);
    }
}
