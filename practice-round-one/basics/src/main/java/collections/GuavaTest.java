package collections;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.util.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/2-18:23
 */
public class GuavaTest {

    @Test
    public void testCreate() {

        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        HashSet<Integer> integers1 = Sets.newHashSet(1, 2, 3, 4);
    }


    @Test
    public void testList() {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5);
        // 翻转
        List<Integer> reverse = Lists.reverse(integers);
        System.out.println("reverse = " + reverse);

    }

    @Test
    public void testSet() {
        HashSet<Integer> integers1 = Sets.newHashSet(1, 2, 3, 4);
        HashSet<Integer> integers2 = Sets.newHashSet(0, 2, 3, 5);
        // a-b
        Sets.SetView<Integer> difference = Sets.difference(integers1, integers2);
        // a+b
        Sets.SetView<Integer> union = Sets.union(integers1, integers2);
        // 交集
        Sets.SetView<Integer> intersection = Sets.intersection(integers1, integers2);
        System.out.println("difference = " + difference);
        System.out.println("union = " + union);
        System.out.println("intersection = " + intersection);
    }

}
