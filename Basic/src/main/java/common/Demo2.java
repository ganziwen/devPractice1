package common;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/1-22:48
 */
public class Demo2 {
    public static void main(String[] args) {

    }

    public void testIf() {
        boolean bool = true;
//        条件为布尔值,或者能得到布尔值的表达式
        if (bool) {
            System.out.println("bool = " + bool);
        } else {
            System.out.println("bool = " + bool);
        }
    }

    public void testSwitch(String string) {
        switch (string) {
            case "a":
                System.out.println("string = " + string);
                break;
            case "b":
                System.out.println("string = " + string);
                break;
//                一定要 default ,逻辑兜底
            default:
                System.out.println("string = " + string);
        }
    }

    public void testFor() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
        }

        String str = "";
//        死循环
        for (; ; ) {
            System.out.println("这是个死循环");

            /**
             * 见到死循环,一定不要慌,一定要去看退出条件
             */
            if (str.equals("A")) {
                System.out.println("str = " + str);
                break;
            }
        }

//        调用外部接口
        int i = 0;
        for (; ; ) {
            //send http
            // res == null || catch timeout Exception
            i += 1;
            if (i > 3) {
                break;
            }
        }

    }

    public void testWhile(boolean bool) {
        while (bool) {
            System.out.println("bool = " + bool);
        }
    }

    ;
}
