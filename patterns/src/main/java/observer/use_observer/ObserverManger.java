package observer.use_observer;

import observer.Tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ObserverManger
 * @Description 责任链设计模式
 * @date 2021/8/15 0:40
 */
public class ObserverManger {
    // 单例内的属性,都需要去构造器内初始化一下,否则会报语法错
    private final List<ITicketsObserver> observers;

    private ObserverManger() {
        this.observers = new ArrayList<>();
        this.observers.add(new XieChengObserve());
        this.observers.add(new QuNaErObserve());
    }

    private static class ClassHolder {
        private static final ObserverManger holder = new ObserverManger();
    }

    public static ObserverManger of() {
        return ClassHolder.holder;
    }

    public void doReceive(Tickets tickets) {
        for (ITicketsObserver observer : observers) {
            observer.reveive(tickets);
        }
    }

}
