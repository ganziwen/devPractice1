package generic;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import str.StringUtilsFunc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName GenerriFromZhihu
 * @Description
 * @date 2021/7/19 21:38
 * https://zhuanlan.zhihu.com/p/272627510
 */
public class GenerriFromZhihu {
    public static void main(String[] args) {

        GenericWithInter genericWithInter = new GenericWithInter();
        GenericWithClass genericWithClass = new GenericWithClass();
        genericWithInter.testGenericWithInter();
        genericWithClass.testGenericWithClass();

    }


}

/**
 * 自定义泛型接口
 */
class GenericWithInter {
    /**
     * 自定义泛型接口
     */
    public interface MyList<E> {
        void showTypeName(E e);
    }

    /**
     * 抽象类实现上述接口
     *
     * @param <E>
     */
    public static class MyListImpl<E> implements MyList<E> {
        @Override
        public void showTypeName(E e) {
            // 打印 e 的数据类型,也就是 E
            System.out.println(e.getClass().getTypeName());
        }
    }

    /**
     * 调用
     */
    public void testGenericWithInter() {
        MyListImpl<Object> myList = new MyListImpl<>();
        myList.showTypeName("HELLO");
    }

}

/**
 * 自定义泛型类
 */
class GenericWithClass {

    /**
     * 泛型类
     *
     * @param <G>
     */
    @Getter
    @Setter
    class Test<G> {
        private G g;
    }

    /**
     * 调用
     */
    public void testGenericWithClass() {
        Test<String> test = new Test<>();
        test.setG("自定义的泛型类");
        System.out.println(test.getG());
    }

    /**
     * 继承泛型类
     * - 继承成为实体类,那么泛型的类型一定要指定
     */
    class GenericSucceed extends Test<String> {

    }

    /**
     * 通配符号,不加的话默认是 Object
     */
    public void testGenericAll(ArrayList<?> list) {

        String typeName = list.getClass().getTypeName();
        System.out.println(typeName);
    }
}


/**
 * 泛型方法
 */
class GenericWithMethod {

}