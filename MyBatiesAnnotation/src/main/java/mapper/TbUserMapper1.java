package mapper;


import bean.TbUser1;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TbUserMapper
 * @Description
 * @date 2021/8/8 14:05
 */
public interface TbUserMapper1 {
    @Select("select * from tb_user where user_id = #{user_id}")
    List<TbUser1> selectByUserId(String userId);
}
