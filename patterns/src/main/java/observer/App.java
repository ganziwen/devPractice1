package observer;


import observer.no_observer.AirComp;
import observer.use_observer.ObserverManger;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName App
 * @Description 监听器设计模式
 * @date 2021/8/15 0:01
 */
public class App {
    public static void main(String[] args) {
        testNoObserve();
        testUseObserve();
    }

    private static void testNoObserve() {
        Tickets tickets = Tickets.builder().amount(100).src("Beijing").dst("ShangHai").build();
        new AirComp().send(tickets);
    }

    private static void testUseObserve() {
        Tickets tickets = Tickets.builder().amount(100).src("Beijing").dst("ShangHai").build();
        new AirComp().send(tickets);

    }

}
