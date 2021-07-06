package generic;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 容器:val1,val2,不使用泛型的方式
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-16:23
 */
@Data
@AllArgsConstructor
public class LocalPair1 {

    private Object val1;
    private Object val2;
}
