package mapper;


import bean.TbUser1;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TbUserMapper
 * @Description
 * @date 2021/8/8 14:05
 */
public interface TbUserMapper1 {

    /**
     * 插入,返回影响的行数
     *
     * @param tbUser1
     * @return
     */
    @Insert("insert into `tb_user` (`user_id`,`user_name`) values (#{userId},#{userName})")
    int insertUser1(TbUser1 tbUser1);

    /**
     * 插入成功返回 true,不成功返回 false
     *
     * @param tbUser1
     * @return
     */
    @Insert("insert into `tb_user` (`user_id`,`user_name`) values (#{userId},#{userName})")
    boolean insertUser2(TbUser1 tbUser1);

    /**
     * 返回主键
     *
     * @param tbUser1
     * @return
     */
    @Insert("insert into `tb_user` (`user_id`,`user_name`) values (#{userId},#{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser3(TbUser1 tbUser1);


    @Update("update `tb_user` set user_id = #{userId}, user_name = #{userName} where 1=1 and id = #{id}")
    int updateUser1(TbUser1 tbUser1);


    @Delete("delete from `tb_user`  where 1=1 and id = #{id}")
    int deleteUser1(TbUser1 tbUser1);

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    @Select("select * from tb_user where user_id = #{userId}")
    List<TbUser1> selectByUserId(String userId);


    /**
     * 多参数形式，需要注意的是,selectByUser1 内的参数名称无关紧要,重要的是 @Param("userId") 内的名称要与 Seclet 内的 #{userId} 参数名对应
     *
     * @param userId
     * @param userName
     * @return
     */
    @Select("select * from `tb_user` where `user_id` = #{userId} and `user_name` = #{userName}")
    List<TbUser1> selectByUser1(@Param("userId") String userId, @Param("userName") String userName);

    @Select("")
    List<TbUser1> selectUserForDynamic(TbUser1 tbUser1);
}
