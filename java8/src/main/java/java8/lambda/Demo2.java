package java8.lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo1
 * @Description
 * @date 2021/7/25 9:47
 */
public class Demo2 {
    public static void main(String[] args) {
        foo1();
        foo2();
    }

    /**
     * 利用匿名内部类
     */
    public static void foo1() {
        List<String> strings = Lists.newArrayList("a1", "a2", "b1", "b2", "a3");
        String res = strings.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("a");
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                })
                .collect(Collectors.joining("+"));
        System.out.println("res = " + res);

    }

    /**
     * 利用 lanbda 以及 stream
     */
    public static void foo2() {
        List<String> strings = Lists.newArrayList("a1", "a2", "b1", "b2", "a3");
        String res = strings.stream().filter(str -> str.startsWith("a")).map(String::toUpperCase).collect(Collectors.joining("+"));
        System.out.println("res = " + res);
    }
}
