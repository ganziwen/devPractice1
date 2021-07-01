package common;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/1-22:48
 */
public class Demo1 {
    public static void main(String[] args) {
//        变量定义
        int i = 1024;
        long l = 1024;
        float f = 3.14f;

//        数组定义
//        1. 不写大小是会报错的,不知道要定义多大
        int[] arr = new int[5];

//        数组越界问题
        /**
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 100
         */
//        arr[100] = 1024;

    }
}
