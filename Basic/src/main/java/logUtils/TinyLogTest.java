package logUtils;

import org.testng.annotations.Test;
import org.tinylog.Logger;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/12/30-12:04
 * https://mp.weixin.qq.com/s/j-ANprxoHzIfQG5DZDQDsg
 */
public class TinyLogTest {
    @Test
    public void testTinyLog() {
        String string = "tinyLog";
        Logger.info("这是测试的{}内容", string);
    }
}
