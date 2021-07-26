package builder;


import lombok.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-18:38
 */

@Getter
@Setter
@Builder
@ToString
public class Person {
    private String name;
    private int age;
    private int height;
}
