package tools.apache.commons.collections;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/16-21:06
 */
public class TestCollections {
    @Test
    public void testCollection() {
        ArrayList<Integer> arrayList1 = Lists.newArrayList(1, 2, 3, 4);
        ArrayList<Integer> arrayList2 = Lists.newArrayList(2, 3, 5, 6, 7);
        ArrayList<Integer> arrayList3 = Lists.newArrayList(1, 2, 3, 4);
        // 判断是否为空
        System.out.println("CollectionUtils.isEmpty(arrayList1) = " + CollectionUtils.isEmpty(arrayList1));
        // 判断是否不为空
        System.out.println("CollectionUtils.isNotEmpty(arrayList2) = " + CollectionUtils.isNotEmpty(arrayList2));
        // 取交集
        System.out.println("CollectionUtils.retainAll(arrayList1,arrayList2) = " + CollectionUtils.retainAll(arrayList1, arrayList2));
        // 取并集
        System.out.println("CollectionUtils.union(arrayList1,arrayList2) = " + CollectionUtils.union(arrayList1, arrayList2));
        // 取差集,前面有的后面没有的
        System.out.println("CollectionUtils.subtract(arrayList1,arrayList2) = " + CollectionUtils.subtract(arrayList1, arrayList2));
        // 判断是否相等
        System.out.println("CollectionUtils.isEqualCollection(arrayList1,arrayList2) = " + CollectionUtils.isEqualCollection(arrayList1, arrayList2));
        System.out.println("CollectionUtils.isEqualCollection(arrayList1,arrayList3) = " + CollectionUtils.isEqualCollection(arrayList1, arrayList3));

    }
}
