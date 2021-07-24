package java8.lambda;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo
 * @Description
 * @date 2021/7/18 20:17
 * <p>
 * 使用 -> 分左右两边
 * 左边:括号内是写参数列表,参数列表只有一个参数时，()可以省略
 * - 参数是可以有多个,多个时是一定要加 () 的,即使没有参数也要写一个空的 ()
 * - 参数列表就是接口内抽象方法的参数列表,也就是示例中 run() 的参数列表
 * - 当只有一个参数时,可以省略括号
 * <p>
 * 右边:方法体
 * - 方法体需要用 {} 括起来
 * - 方法体只有一行,可以省略 {}, 多行当然不能省略
 * - 方法体只有一行且直接 return ,可以省略 return
 */
public class Demo1 {
    public static void main(String[] args) {

        testNormal();
        testLambda1();
        testLambda2();

        testCompareLambda();
        testlambdaOneparam();

        testMyFunctionalInterFace();


    }

    /**
     * 老的方式创建匿名内部类,其实真正的任务只有一行:System.out.println("Demo1.run");
     */
    public static void testNormal() {
        // 利用 Alt + Enter 可以进行替换成 lambda 表达式
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Demo1.run");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 利用 stream 的简写方式
     */
    public static void testLambda1() {
        Runnable runnable = () -> {
            System.out.println("Demo1.testStream1");
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 再简化一下
     */
    public static void testLambda2() {
        // 方法体内只有一行，则可以把 {} 去掉
        Runnable runnable = () -> System.out.println("Demo1.testStream2");
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * 比较器
     * - o1 和 o2 的数据类型会自动推断,所以不用自己写
     * - 方法体内只有一行 return ,那么 return 也可以省略
     */
    public static void testCompareLambda() {
        List<String> list = Lists.newArrayList("q", "qwe", "qwert", "sjduen", "jhgs");

        // 按数据长度由小到大排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println("list = " + list);

        // 按数据长度由小到大排序,利用 lambda 方式简写
        Collections.sort(list, (o1, o2) -> o1.length() - o2.length());
        System.out.println("list = " + list);
    }

    /**
     * 参数列表只有一个参数的情况
     */
    public static void testlambdaOneparam() {
        // 将字符转小写
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toLowerCase();
            }
        };
        // s 加上括号也不会报错
        Function<String, String> function2 = s -> s.toLowerCase();
        String str = function2.apply("HELLO");
        System.out.println("str = " + str);
    }


    /**
     * @FunctionalInterface 代表该接口只能有一个抽象方法, 这种接口叫做函数式接口
     */
    @FunctionalInterface
    interface Foo1 {
        int foo1(int x);
    }

    public static void testMyFunctionalInterFace() {
        Foo1 foo1 = x -> x + 10;
    }
}