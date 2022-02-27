package reflect;

import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName User
 * @Description
 * @date 2022/2/27 20:52
 */
@Data
public class User {
    private String userName;
    private Integer age;

    public String getUserInfo() {
        return String.format("name is" + userName, "age is" + age);
    }

    public static void getMethodPrint() {
        System.out.println("User.getClassInfo");
    }
}
