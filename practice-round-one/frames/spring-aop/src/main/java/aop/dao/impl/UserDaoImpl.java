package aop.dao.impl;

import aop.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName UserDaoImpl
 * @Description
 * @date 2022/3/13 15:54
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void daoInfo(String name, int age) {
        System.out.println(String.format("UserDaoImpl.daoInfo,name is %s,age is %s", name, age));
    }
}
