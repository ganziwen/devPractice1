package collections.list;

import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/2-14:09
 */
public class TestArrayList {
    /**
     * 增删改查
     */
    @Test
    public void testArrayList1() {
        // 创建新 list
        List<String> stringList = new ArrayList<>();
        List<String> stringList2 = new ArrayList<>();


        stringList.add("first");
        stringList.add("second");

        stringList2.add("one");
        stringList2.add("two");
        stringList2.add("modify");

        // 角标不存在会报错:IndexOutOfBoundsException
        stringList.remove(0);
        // 元素不存在不会报错
        stringList.remove("first");
        System.out.println("stringList = " + stringList);

        // 修改,其实理解用到角标的,不存在就会报错
        stringList.set(0, "modify");
        System.out.println("stringList = " + stringList);

        // 按角标查询
        System.out.println("stringList.get(0) = " + stringList.get(0));

        // 将 stringList 内剔除 stringList2 的元素
        stringList.removeAll(stringList2);
        System.out.println("stringList = " + stringList);

        // 截取,从 0 开始,往后取 1 个
        List<String> stringList1 = stringList.subList(0, 1);

        // 将 list 清空
        stringList2.clear();

    }

    /**
     * contain and containAll
     */
    @Test
    public void testArrayList2() {
        User user1 = User.builder().name("user1").age(10).build();
        User user1New = User.builder().name("user1").age(10).build();
        User user2 = User.builder().name("user2").age(20).build();
        List<User> userList1 = new ArrayList<>();
        List<User> userList2 = new ArrayList<>();
        userList1.add(user1);
        userList2.add(user2);
        userList2.add(user1New);

        // contain 其实用的就是 equals 进行判断
        if (userList2.contains(user1)) {
            System.out.println("应用的是 equals ");
        }

        if (userList2.containsAll(userList1)) {
            System.out.println("1在2内");
        }

    }

    /**
     * indexOf and lastIndexOf
     */
    @Test
    public void testIndex() {
        List<String> stringList = new ArrayList<>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("two");
        stringList.add("three");
        System.out.println("stringList.indexOf(\"two\") = " + stringList.indexOf("two"));
        System.out.println("stringList.lastIndexOf(\"two\") = " + stringList.lastIndexOf("two"));
    }

    /**
     * arrayToList and ListToArray
     */
    @Test
    public void testArrayTransfer() {
        String[] strings = new String[]{"one", "two", "three"};
        // 数组转 list
        List<String> stringList = Arrays.asList(strings);
        // list 转数组
        String[] toArray = stringList.toArray(new String[]{});
        for (String s : toArray) {
            System.out.println(s);
        }
    }

    /**
     * iterator
     */
    @Test
    public void testIterator() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("4222");
        list.add("3423");
        // 生成个迭代器
        Iterator<String> stringIterator = list.iterator();
        // 判断迭代器是否有下一个元素
        while (stringIterator.hasNext()) {
            if ("4222".equals(stringIterator.next())) {
                // 注意这里的删除是要用迭代器去删除, 而不能使用 list
                stringIterator.remove();
            }
        }
        // 这个功能跟上面的一致
        list.removeIf(Predicate.isEqual("3423"));

        System.out.println("list = " + list);
    }

    /**
     *
     */
    @Test
    public void testSort() {
        User name1 = User.builder().name("name2").age(20).build();
        User name2 = User.builder().name("name1").age(10).build();
        User name3 = User.builder().name("name3").age(30).build();
        List<User> list = new ArrayList<>();
        list.add(name1);
        list.add(name2);
        list.add(name3);
        // 内部类实现一个 compare 方法,就是个比较器
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        // 利用 lambda 简化的操作
        list.sort((o1, o2) -> o1.getAge() - o2.getAge());

        System.out.println("list = " + list);
    }

}
