package java8.stream;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/30-18:14
 */
public class StreamDemo1 {
    public static final List<String> list = Lists.newArrayList("abcd", "xyz", "789", "axy", "azz");

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("asd", "a", "wer", "zxc", "aaaa", "12345", "abcde");
        // 1. 获取流,这个是集合流
        Stream<String> stream = list.stream();

        // 2. 数组流
        Arrays.stream(new String[]{});

        // 3. 获取并行流:数据很多并行处理,要是自己写多线程很麻烦
        list.parallelStream();

        // 数组流转换成并行流
        Stream<String> stream1 = Arrays.stream(new String[]{});
        stream1.parallel();  // 转换成并行流

        // 流的操作:中间操作,可以有很多个
        list.stream()
                // 过滤(断言型)
                .filter(str -> str.startsWith("a"))
                //打印(消费型)
                .peek(str -> System.out.println("filter:" + str))// filter:asd
                // 转大写(转化型)
                .map(str -> str.toUpperCase())
                .peek(str -> System.out.println("map:" + str))// map:ASD
                // 排序
                .sorted((o1, o2) -> o1.length() - o2.length())
                .limit(3)// 取前 3
                .skip(1)// 跳过第 1 个
                // 结束操作(消费型),一定要跟结束型,否则没有任何输出
                .forEach(str -> System.out.println(str))
        ;

        System.out.println("----------");

        list = Lists.newArrayList("a", "a", "wer", "asd", "aaaa", "asd", "abcde");
        list.stream().
                // 排除重复
                        distinct().
                // 流的操作:结束操作
                        forEach(x -> System.out.println(x));
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
}
