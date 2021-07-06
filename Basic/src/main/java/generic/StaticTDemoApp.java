package generic;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-18:30
 */
public class StaticTDemoApp {
    public static void main(String[] args) {
        String data1 = (String) StaticTDemo.returndata("123");
        System.out.println("data1 = " + data1); // data1 = 123

        String data = StaticTDemo.getData("123");
        System.out.println(data.length());
        System.out.println("data = " + data); // data = 123
    }
}

