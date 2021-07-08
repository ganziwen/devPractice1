package collection;

import java.util.*;

/**
 * 列表操作
 *
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/8-17:36
 */
public class ListFunc {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("asd");
        arrayList.add("123");
        System.out.println("arrayList = " + arrayList);

        List<String> arrayList2 = new ArrayList<>();
        arrayList2.add("12345");
        arrayList.addAll(arrayList2);
        System.out.println("arrayList = " + arrayList);

        arrayList.remove(0);
        System.out.println("arrayList = " + arrayList);
        arrayList.removeAll(arrayList2);
        System.out.println("arrayList = " + arrayList);

        arrayList.set(0, "666");
        System.out.println("arrayList = " + arrayList);

        System.out.println("arrayList.get(0) = " + arrayList.get(0));

        arrayList.clear();
        System.out.println("arrayList = " + arrayList);

        arrayList.add("111");
        arrayList.add("88");
        arrayList.add("111");
        arrayList.add("121");


        arrayList2.clear();
        arrayList2.add("88");
        arrayList2.add("2");

        System.out.println("arrayList.contains(\"111\") = " + arrayList.contains("111"));
        System.out.println("arrayList.containsAll(arrayList2) = " + arrayList.containsAll(arrayList2));

        System.out.println("arrayList.indexOf(\"111\") = " + arrayList.indexOf("111"));
        System.out.println("arrayList.lastIndexOf(\"111\") = " + arrayList.lastIndexOf("111"));

        String[] strings = arrayList.toArray(new String[]{});
        System.out.println("Arrays.toString(strings) = " + Arrays.toString(strings));

        arrayList2 = arrayList.subList(0, 1);
        System.out.println("arrayList2 = " + arrayList2);

//        迭代器
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println("str = " + str);
        }

    }
}
