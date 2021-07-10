package reflect;

import lombok.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName User
 * @Description
 * @date 2021/7/10 12:37
 */

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    /**
     * 这个类本身属于 Class这个类的对象
     * 属性本身属于 Field 这个类的对象
     * 方法本身属于 Method 这类的个对象
     * 构造器属于 ConStructor 这个类的对象
     */
    private String name;
    private String age;
    public String sex;

    public void foo1(String name, String age, String sex) throws RuntimeException {

    }

    // 私有方法默认拿不到
    private void makeLove() {
        System.out.println("User.makeLove");
    }
}
