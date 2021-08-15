package observer.use_observer;

import observer.Tickets;

import java.util.Observable;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AirComp
 * @Description
 * @date 2021/8/15 0:52
 */
public class AirComp {
    public void send(Tickets tickets) {
        ObserverManger.of().doReceive(tickets);
    }
}

abstract class ITicketsObserver {
    /**
     * 定义平台的收票接口
     *
     * @param tickets
     */
    void reveive(Tickets tickets) {

    }
}

class QuNaErObserve extends ITicketsObserver {
    @Override
    void reveive(Tickets tickets) {
        System.out.println("QuNaErObserve.reveive" + tickets.toString());
    }
}

class XieChengObserve extends ITicketsObserver {
    @Override
    void reveive(Tickets tickets) {
        System.out.println("XieChengObserve.reveive" + tickets.toString());
    }
}
