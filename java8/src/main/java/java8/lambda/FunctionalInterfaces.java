package java8.lambda;

import java.time.LocalDateTime;
import java.util.function.*;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FunctionalInterfaces
 * @Description
 * @date 2021/7/24 15:33
 * 函数式接口类型:包 java.util.function 下的都是 java8 给我们写好的函数式接口,无需自己再去定义
 * - java.util.function.Function<T, R>: 转化型
 * - java.util.function.Consumer<T>: 消费型
 * - java.util.function.Supplier<T>: 供给型
 * - java.util.function.Predicate<T>: 断言型
 */
public class FunctionalInterfaces {
    public static void main(String[] args) {
        testFunction();

        testConsumer();
    }

    /**
     * 转化型:进来的是前一个数据类型,返回的是第二个类型
     */
    public static void testFunction() {
        System.out.println("转化型");
        // 匿名内部类方式
        Function<String, Integer> function1 = new Function<String, Integer>() {
            @Override
            // 进来的的是 String 类型
            public Integer apply(String s) {
                // 返回的是 Int 类型
                return s.length();
            }
        };

        Integer res1 = function1.apply("HelloWorld");
        System.out.println("res1 = " + res1);

        // lambda 方式
        Function<String, Integer> function2 = s -> s.length();
        Integer res2 = function2.apply("HelloWorld");
        System.out.println("res2 = " + res2);

    }

    /**
     * 消费型:进来的类型,返回的是 void 类型
     */
    public static void testConsumer() {
        System.out.println("消费型");
        /**
         * 这里要注意new的Consumer 后面要接数据类型
         */
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String str) {
                int length = str.length();
                System.out.println("length = " + length);
            }
        };
        consumer1.accept("helloworld");

        Consumer<String> consumer2 = str -> {
            int length = str.length();
            System.out.println("length = " + length);

        };
        consumer2.accept("helloworld");

    }

    /**
     * 进来的为空,返回要的类型;其实可以理解为跟消费者是相对的
     */
    public static void testSupplier() {
        Supplier<LocalDateTime> supplier1 = new Supplier<LocalDateTime>() {
            @Override
            public LocalDateTime get() {
                return LocalDateTime.now();
            }
        };
        supplier1.get();

        Supplier<LocalDateTime> supplier2 = () -> LocalDateTime.now();
        supplier2.get();
    }

}
