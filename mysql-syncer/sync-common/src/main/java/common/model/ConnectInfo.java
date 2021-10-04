package common.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ConnectInfo
 * @Description 数据库连接信息的实体类
 * @date 2021/8/29 10:26
 */
@Data
public class ConnectInfo {
    @NotBlank(message = "ConnectInfo.url can't be blank")
    private String url;

    @NotBlank(message = "ConnectInfo.userName can't be blank")
    private String userName;

    @NotBlank(message = "ConnectInfo.passWord can't be blank")
    private String passWord;

}
