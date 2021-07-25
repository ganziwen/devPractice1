package java8.lambda;

import java.util.Comparator;
import java.util.function.Function;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MethodReference
 * @Description
 * @date 2021/7/24 18:15
 * 方法引用
 */
public class MethodReference {
    public static void main(String[] args) {
        testStaticFuncRefer();
        testInstanceFuncRefer();
    }


    /**
     * 静态方法引用
     */
    public static void testStaticFuncRefer() {
        // lambda 表达式
        Comparator<Integer> comparator1 = (o1, o2) -> Integer.compare(o1, o2);
        int res1 = comparator1.compare(1024, 2048);
        System.out.println("res1 = " + res1);

        // lambda 表达式，加入静态方法引用
        Comparator<Integer> comparator2 = Integer::compare;
        int res2 = comparator2.compare(1024, 2048);
        System.out.println("res2 = " + res2);
    }

    /**
     * 实例方法引用
     */
    public static void testInstanceFuncRefer() {
        Function<String, String> function1 = str -> str.toLowerCase();
        String apply1 = function1.apply("HELLOWORLD");
        System.out.println("apply1 = " + apply1);

        Function<String, String> function2 = String::toLowerCase;
        String apply2 = function2.apply("HELLOWORLD");
        System.out.println("apply2 = " + apply2);
    }


}
