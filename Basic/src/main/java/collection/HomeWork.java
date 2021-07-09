package collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/9-13:54
 */
public class HomeWork {
    public static void main(String[] args) {
        List list1 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List list2 = Lists.newArrayList(4, 5, 6, 7, 8, 9, 0);
        List list = test1(list1, list2);
        System.out.println("list = " + list);

        Set set = test2(list1, list2);
        System.out.println("set = " + set);
    }

    /**
     * 取两个 list 的相同项,for 循环解决
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List test1(List list1, List list2) {
        List list = Lists.newArrayList();
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (list2.get(j).equals(list1.get(i))) {
                    list.add(list1.get(i));
                }
            }
        }
        return list;

    }

    /**
     * 取两个 list 的相同项,利用 Set 解决
     *
     * @param list1
     * @param list2
     * @return
     */
    public static Set test2(List list1, List list2) {
        Set set1 = new HashSet(list1);
        Set set2 = new HashSet(list2);

        Set set = Sets.intersection(set1, set2);
        return set;
    }

    public static Map test3(Map map) {
//        Set set = Sets.sort;
        return map;
    }
}
