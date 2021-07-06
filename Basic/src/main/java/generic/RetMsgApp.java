package generic;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-18:14
 */
public class RetMsgApp {
    public static void main(String[] args) {
        RetMsg<String> retMsg1 = RetMsg.buildSuccessMsg("hello");

        // 效果同上,但是没有使用泛型方法
        RetMsg<String> retMsg2 = new RetMsg<>(200, "success", "hello");

    }
}
