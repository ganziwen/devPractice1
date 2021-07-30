package course.spring.spring_normal_usage.service.impl;

import course.spring.spring_normal_usage.dao.PingDao;
import course.spring.spring_normal_usage.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-15:29
 *
 * <bean id = "PingServiceImpl" class="PingServiceImpl">
 * <property name = "pingDao" ref = "PingDao">
 * </bean>
 */

@Service
public class PingServiceImpl implements PingService {

    @Autowired
    private PingDao pingDao;

    @Override
    public String ping(String res) {
        return String.format("PingServiceImpl.res=%s,PingDao.dao=%s", res, pingDao.dao("PingService.dao"));
    }
}
