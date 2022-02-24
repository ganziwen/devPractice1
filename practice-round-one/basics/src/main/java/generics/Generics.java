package generics;

import lombok.Data;
import org.testng.annotations.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Generics
 * @Description 泛型类
 * @date 2022/2/24 21:54
 */
public class Generics {

    /**
     * 测试泛型类
     */
    @Test
    public void testClass() {
        // 用泛型类，定义的时候定义其类型
        TestGenericClass<String, Integer> testClass = new TestGenericClass<>();
        testClass.setAge(18);
        testClass.setName("ganziwen");
        System.out.println(testClass.toString());
    }

    /**
     * 测试泛型方法
     */
    @Test
    public void testMethod() {
        TestGenericClass<Object, Object> genericClass = new TestGenericClass<>();
        genericClass.checkInputInstance(1234455);
    }

    @Test
    public void testHardWay() {
        TestGenericClass<String, Integer> testClass = new TestGenericClass<>();
        testClass.setAge(18);
        testClass.setName("ganziwen");
        RetMsg<TestGenericClass<String, Integer>> successRetMsg =
                RetMsg.buildSuccessMsg(testClass);
        System.out.println("successRetMsg = " + successRetMsg.toString());

        RetMsg<TestGenericClass<String, Integer>> errRetMsg = RetMsg.buildFailMsg(testClass);
        System.out.println("errRetMsg = " + errRetMsg);
    }
}


/**
 * @param <K> name 是名字,有可能是一个
 * @param <V> age 是一个 int 或者是 double 甚至是 bigDe 类型
 */
@Data
class TestGenericClass<K, V> {
    K name;
    V age;

    /**
     * 泛型方法,<T> 标志为用了泛型, T 代表返回值的类型,无返回值的话,直接用 void
     *
     * @param data
     * @param <T>
     * @return
     */
    public <T> T checkInputInstance(T data) {
        String format = "传入的 data 是 [%s] 类型";
        if (data instanceof Integer) {
            System.out.println(String.format(format, "Integer"));
        } else if (data instanceof Double) {
            System.out.println(String.format(format, "Double"));
        } else if (data instanceof String) {
            System.out.println(String.format(format, "String"));
        } else {
            System.out.println(String.format(format, "其他"));
        }

        return data;
    }
}

@Data
class RetMsg<T> {
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERR_CODE = 500;
    private static final String SUCCESS_MSG = "success";
    private static final String ERR_MSG = "failed";

    private Integer errCode;
    private String errMsg;
    private T data;

    /**
     * 构造进去
     *
     * @param errCode
     * @param errMsg
     * @param data
     */
    public RetMsg(Integer errCode, String errMsg, T data) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }


    public static <R> RetMsg<R> buildSuccessMsg(R data) {
        return new RetMsg<>(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <R> RetMsg<R> buildFailMsg(R data) {
        return new RetMsg<>(ERR_CODE, ERR_MSG, data);
    }
}

