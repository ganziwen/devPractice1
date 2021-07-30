package course.spring.spring_with_value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Person
 * @Description
 * @date 2021/7/30 22:09
 */

@Data
@Component
public class Person {


    @Value("Bob")
    String name;
    @Value("18")
    int age;

}
