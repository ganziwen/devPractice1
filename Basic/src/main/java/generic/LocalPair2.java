package generic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 利用泛型
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-16:59
 */

@Data
@AllArgsConstructor
public class LocalPair2<k, v> {
    // 数据类型是一个变量
    private k val1;
    private v val2;
}
