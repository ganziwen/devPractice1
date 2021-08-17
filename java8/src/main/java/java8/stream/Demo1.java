package java8.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo
 * @Description
 * @date 2021/7/25 10:19
 */
public class Demo1 {
    public static final List<String> list = Lists.newArrayList("abcd", "xyz", "789", "axy", "azz");

    public static void main(String[] args) {
        foo1();
        foo2();
        foo3();
    }

    /**
     * 不利用 stream 流
     * 过滤字符串,找到以 a 开头的,排序后生成一个字符串,并用 , 进行分割
     * 例如；abc,axy,azz  ->  “abc,axy,azz”
     */
    public static void foo1() {
        String res = list.stream().filter(s -> s.startsWith("a")).sorted(Comparator.comparing(String::length)).collect(Collectors.joining(","));
        System.out.println("res = " + res);
    }

    /**
     * 流组成:流化操作+中间操作+结束操作
     */
    public static void foo2() {
        list.stream()   // 流化操作
                .filter(s -> s.startsWith("a")) // 中间操作,判断是不是结束操作,看 filter 的返回类型即可,返回为 Stream<T> 说明不是结束操作
                .map(String::toUpperCase)  // 中间操作,判断是不是结束操作,看 filter 的返回类型即可,返回为 Stream<R> 说明不是结束操作
                .forEach(System.out::println);  // 结束操作,方法返回值为 void
    }

    public static void foo3() {
        System.out.println("++++++++++++");
        List<List<String>> list2 = new ArrayList<>();
        list2.add(Lists.newArrayList("a123", "b345", "c456"));
        list2.add(Lists.newArrayList("asdf", "qwev", "zxcr"));
        list2.add(Lists.newArrayList("aaa", "bbb", "cccc"));
        list2.stream().flatMap(single -> single.stream()).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
    }
}
