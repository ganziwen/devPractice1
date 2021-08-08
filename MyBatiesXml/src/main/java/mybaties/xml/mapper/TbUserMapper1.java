package mybaties.xml.mapper;

import mybaties.xml.bean.TbUser1;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TbUserMapper
 * @Description
 * @date 2021/8/8 14:05
 */
public interface TbUserMapper1 {

    List<TbUser1> selectByUserId(String userId);
}
