package generic;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-18:07
 */
public class RetMsg<T> {
    private static final Integer SUCCESS_CODE = 200;
    private static final String SUCCESS_MSG = "success";

    private Integer errorCode;
    private String errorMessage;
    // data 的数据类型不确定,就给定义成泛型
    private T data;

    public RetMsg(Integer errorCode, String errorMessage, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    /**
     * 静态
     *
     * @param data
     * @param <R>
     * @return
     */
    public static <R> RetMsg<R> buildSuccessMsg(R data) {
        return new RetMsg<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }
}
