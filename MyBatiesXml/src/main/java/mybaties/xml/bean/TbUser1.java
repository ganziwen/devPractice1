package mybaties.xml.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TbUser
 * @Description 数据库的实体 bean
 * @date 2021/8/8 14:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbUser1 {
    private Long id;
    private String userId;
    private String userName;

}

