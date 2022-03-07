package collections.list;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/2-15:17
 */
@Data
@Builder
public class User {
    private String name;
    private Integer age;
}
