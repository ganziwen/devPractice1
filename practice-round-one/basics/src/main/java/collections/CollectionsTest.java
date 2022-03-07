package collections;

import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/3/2-20:51
 */
public class CollectionsTest {
    @Test
    public void testCollection() {
        ArrayList<Integer> integers = Lists.newArrayList(9, 2, 6, 4, 5);

        // 随机打乱
        Collections.shuffle(integers);
        System.out.println("integers = " + integers);

        // 排序
        Collections.sort(integers);
        System.out.println("integers = " + integers);

        // 翻转
        Collections.reverse(integers);
        System.out.println("integers = " + integers);

        Integer min = Collections.min(integers);
        System.out.println("min = " + min);
        
    }

}
