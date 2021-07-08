package collection;

import java.util.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/8-18:30
 */
public class SetFunc {
    public static void main(String[] args) {
        Set set = new HashSet();
        System.out.println("set = " + set);
        set.add("123");
        set.add("asd");
        set.add("123");
        set.add(666);
        System.out.println("set = " + set);

        Set set2 = new HashSet();
        set2.add("000");

        set.addAll(set2);
        System.out.println("set = " + set);

        set2.remove("000");
        System.out.println("set2 = " + set2);

        set.removeAll(set2);
        System.out.println("set = " + set);

        set2.addAll(set);
        set2.clear();
        System.out.println("set2 = " + set2);

        set2.add("123");
        System.out.println("set.contains(\"22\") = " + set.contains("22"));

        System.out.println("set.containsAll(set2) = " + set.containsAll(set2));

//        集合的各个元素不一定一致,所以用 Object 接收
        Iterator<Object> iterator = set.iterator();
        while (iterator.hasNext()) {
            Object str = iterator.next();
            System.out.println("str = " + str);
        }

        List<String> arrayList = new ArrayList<>();
        arrayList.add("123");
        arrayList.add("345");
//        list 转 set
        Set setList = new HashSet(arrayList);
        System.out.println("setList = " + setList);

        Set setSet = new HashSet();
        setSet.add("123");
        setSet.add("999");
//        set 转 list
        List<String> arrayList2 = new ArrayList<>(setSet);
        System.out.println("arrayList2 = " + arrayList2);
    }
}
