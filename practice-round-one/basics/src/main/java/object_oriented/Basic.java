package object_oriented;

import com.sun.istack.internal.NotNull;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/2/8-18:18
 * 面向对象
 */
public class Basic {

    @Test
    public void testCreateOneObject() {
        Students students = new Students();
        System.out.println(students.getNameAndAge(18));
        System.out.println(students.getNameAndAge("方法重载第一种"));
        System.out.println(students.getNameAndAge("方法重载第二种", 19));
        System.out.println(students.joinAllArguments("哈哈", "这是啥"));
    }


    public void change(int a, int[] arr, String string) {
        a = a + 1;
        arr[0] = 9999;
        string = string + "add";
    }

    /**
     * 值传递和引用传递
     */
    @Test
    public void transfer() {
        int a = 3;
        int[] arr1 = {1, 2, 3};
        String test = "test";
        change(a, arr1, test);
        System.out.println("a = " + a);
        System.out.println("Arrays.toString(arr1) = " + Arrays.toString(arr1));
        System.out.println("test = " + test);
    }

}
