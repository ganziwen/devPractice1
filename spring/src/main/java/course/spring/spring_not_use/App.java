package course.spring.spring_not_use;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/27-10:32
 * 未用到 Spring,缺点还是耦合严重.
 * 接口是"协议""约定",实现类是实现方式,App 就是客户端,这三层的理解
 */

/**
 * 使用方 App 与实现 HelloServiceImpl 耦合在了一起
 */
public class App {
    public static void main(String[] args) {
        // 问题就在这,针对使用者而言,其实应该只关注接口,不需要去关注具体实现类,实现方法是谁
        HelloService hello = new HelloServiceImpl();
        String msg = hello.sayHi("Spring not used!");
        System.out.println(msg);
    }
}
