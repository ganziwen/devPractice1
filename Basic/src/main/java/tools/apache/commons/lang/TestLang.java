package tools.apache.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/17-10:28
 * empty 是判断字符串的长度是否 > 0,blank 是判断 strim 后的长度是否大于 0 ，这样就很好理解了
 */
public class TestLang {

    public void stringInfo(String string) {
        System.out.println(String.format("StringUtils.isNotEmpty(\"%s\") = %s", string, StringUtils.isNotEmpty(string)));
        System.out.println(String.format("StringUtils.isEmpty(\"%s\") = %s", string, StringUtils.isEmpty(string)));
        System.out.println(String.format("StringUtils.isBlank(\"%s\") = %s", string, StringUtils.isBlank(string)));
        System.out.println(String.format("StringUtils.isNotBlank(\"%s\") = %s", string, StringUtils.isNotBlank(string)));
    }

    @Test
    public void testLang() {
        stringInfo("");
        stringInfo("    ");
        stringInfo("  23 ");

    }

}
