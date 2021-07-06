package str;

import org.apache.commons.lang3.StringUtils;

/**
 * StringUtils 工具类方法,其实只是避免了 NPE 异常
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-15:48
 */
public class StringUtilsFunc {
    public static void main(String[] args) {

    }

    /**
     * 全部练习一下功能并且查看一下 StringUtils 的源码
     */
    public static void test1() {
        String string = "hello world";
        StringUtils.isNotEmpty(string);
    }
}
