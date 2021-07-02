package oop;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/2-16:26
 */
public class CarApp {
    public static void main(String[] args) {
        // Car 是我们定义的类,new Car() 之后,生成的 car 就是实例化出来的对象
        Car car = new Car();
        car.run(100);
        int sum = car.getSumMile();
        System.out.println("sum = " + sum);
    }
}
