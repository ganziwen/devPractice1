package mybaties.xml.mapper;

import mybaties.xml.bean.TbUser1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TbUserMapper
 * @Description
 * @date 2021/8/8 14:05
 */
public interface TbUserMapper1 {

    // 单参数
    List<TbUser1> selectByUserId(String userId);

    // 多参数方式1
    List<TbUser1> selectByUser1(@Param("userId") String userId, @Param("userName") String userName);

    // 多参数方式2
    List<TbUser1> selectByUser2(TbUser1 tbUser1);

    // 多参数方式3(不是很推荐)
    List<TbUser1> selectByUser3(String userId, String userName);

    // 多参数方式3(不是很推荐)
    List<TbUser1> selectByUser4(@Param("userId") String userId, @Param("userName") String userName);

    // TODO: 2021/8/10 xml形式的 curd
}
