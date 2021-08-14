package observer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Tickets
 * @Description 票属性
 * @date 2021/8/15 0:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tickets {
    private Integer amount;
    private String src;
    private String dst;
}
