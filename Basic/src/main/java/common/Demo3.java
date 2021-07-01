package common;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/1-22:49
 */
public class Demo3 {
    public static void main(String[] args) {
        int i = 1024;
//        一个是先加,一个是先用
        System.out.println("i++ = " + i++);
        System.out.println("++i = " + ++i);

//        包装类型间的比较应该用 equals 而不是 ==
        Integer i1 = 123;
        Integer i2 = 123;
//        if (i1 == i2) {
//            System.out.println("i1 == i2 = " + i1 == i2);
//        }


        int cc = 5;
        int dd = cc > 6 ? 7 : 8;
        System.out.println("dd = " + dd); // dd = 8
    }
}
