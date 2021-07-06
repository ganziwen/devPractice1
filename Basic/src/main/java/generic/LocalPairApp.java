package generic;

import java.time.LocalDate;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-16:38
 */
public class LocalPairApp {

    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * Object
     *
     * @return
     */
    public static LocalPair1 foo1() {
        LocalPair1 localPair1 = new LocalPair1("test1,test2", "test3,test4");
        // 假设经过了一堆逻辑的处理
        return localPair1;
    }

    public static LocalPair1 foo2() {
        LocalPair1 localPair1 = new LocalPair1(12345, "t,e,s,t");
        return localPair1;
    }

    /**
     * 泛型
     *
     * @return
     */
    public static LocalPair2<String, String> foo3() {
        LocalPair2<String, String> localPair2 = new LocalPair2<>("test1,test2", "test3,test4");
        // 假设经过了一堆逻辑的处理
        return localPair2;
    }

    public static LocalPair2<Integer, String> foo4() {
        LocalPair2<Integer, String> localPair2 = new LocalPair2<>(12345, "t,e,s,t");
        return localPair2;
    }

    /**
     * Object 方式调用
     */
    public static void test1() {
        LocalPair1 localPair1 = foo1();
        Object val1 = localPair1.getVal1();
        Object val2 = localPair1.getVal2();

        // 这里一定是需要强转的,才能转换成对应的数据类型使用 api
        String[] arr1 = ((String) val1).split(",");
        String[] arr2 = ((String) val2).split(",");
        for (String string : arr1) {
            System.out.println("val1 = " + string);
        }
        for (String string : arr2) {
            System.out.println("val2 = " + string);
        }
    }

    /**
     * 泛型方式调用
     */
    public static void test2() {
        LocalPair2<String, String> localPair2 = foo3();
        // 编译就会报错
//        LocalPair2<String, String> localPair3 = foo4();
        String val1 = localPair2.getVal1();
        String val2 = localPair2.getVal2();

        // 这里一定是需要强转的,才能转换成对应的数据类型使用 api
        String[] arr1 = val1.split(",");
        String[] arr2 = val2.split(",");
        for (String string : arr1) {
            System.out.println("val1 = " + string);
        }
        for (String string : arr2) {
            System.out.println("val2 = " + string);
        }
    }

}
