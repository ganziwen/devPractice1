package collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import java.util.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/8-20:20
 */
public class Guavas {
    public static void main(String[] args) {
        testCollection();
        testGoogle();
    }

    public static void testCollection() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
        HashSet<Integer> set1 = Sets.newHashSet(11, 22, 33);
        Collections.shuffle(list1);
        System.out.println("list1 = " + list1);

        Collections.sort(list1);
        System.out.println("list1 = " + list1);

        Collections.reverse(list1);
        System.out.println("list1 = " + list1);
    }

    public static void testGoogle() {
        List<Integer> l1 = Lists.newArrayList(1, 2, 3, 4, 5);
        HashSet<Integer> s1 = Sets.newHashSet(2, 3, 4);
        HashSet<Integer> s2 = Sets.newHashSet(4, 5, 6);

//        list 翻转
        List<Integer> l2 = Lists.reverse(l1);
        System.out.println("l2 = " + l2);

//        s1 s2 取差集
        Set<Integer> different = Sets.difference(s1, s2);
        System.out.println("different = " + different);

//        s1 s2 取交集
        Set<Integer> intersection = Sets.intersection(s1, s2);
        System.out.println("intersection = " + intersection);

//        s1 s2 取并集
        Set<Integer> union = Sets.union(s1, s2);
        System.out.println("union = " + union);
    }
}
