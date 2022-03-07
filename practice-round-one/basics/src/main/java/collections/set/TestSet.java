package collections.set;

import org.testng.annotations.Test;

import java.util.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/2-17:41
 */
public class TestSet {
    /**
     * 只有增加和删除，没有修改
     */
    @Test
    public void testCurd() {
        Set set = new HashSet();
        // 增加
        set.add(1);
        set.add(2);
        set.add("1");
        set.add("2");

        // 删除，只有根据元素去删除,set 没有角标的概念
        set.remove(0);
        System.out.println("set.contains(1) = " + set.contains(1));

        System.out.println("set.contains(1) = " + set.contains(1));

        set.clear();

    }

    @Test
    public void testIterator() {
        Set set = new HashSet();
        // 增加
        set.add(1);
        set.add(2);
        set.add("1");
        set.add("2");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("iterator.next() = " + iterator.next());
        }
    }

    @Test
    public void testTrans() {
        Set set = new HashSet();
        // 增加
        set.add(1);
        set.add(2);
        set.add("1");
        set.add("2");
        // set 转 list
        List<String> list = new ArrayList<>(set);
        System.out.println("list = " + list);

        // list 转 set
        Set set1 = new HashSet(list);
        System.out.println("set1 = " + set1);

    }
}
