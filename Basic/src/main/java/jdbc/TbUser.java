package jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表的对象
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/14-15:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbUser {
    private Long id;
    private String user_id;
    private String user_name;
    private String email;
    private String address;
    private String corp;
}
