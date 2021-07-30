package course.spring.spring_normal_usage.dao.impl;

import course.spring.spring_normal_usage.dao.PingDao;
import org.springframework.stereotype.Repository;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-15:40
 */

@Repository
public class PingDaoImpl implements PingDao {
    @Override
    public String dao(String dao) {
        return String.format("PingDaoImpl.dao:%s", dao);
    }
}
