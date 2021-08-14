package observer.no_observer;

import observer.Tickets;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AirComp
 * @Description
 * @date 2021/8/15 0:12
 */
public class AirComp {
    /**
     * 航空公司把票放出来,让各大平台接收
     *
     * @param tickets
     */
    public void send(Tickets tickets) {
        new XieCheng().receive(tickets);
        new QuNaEr().receive(tickets);
    }
}

/**
 * 各大平台,有接收票的方法
 */
abstract class PlatForm {
    public void receive(Tickets tickets) {
    }
}

class QuNaEr extends PlatForm {
    @Override
    public void receive(Tickets tickets) {
        System.out.println("QuNaEr.receive" + tickets.toString());
    }
}

class XieCheng extends PlatForm {
    @Override
    public void receive(Tickets tickets) {
        System.out.println("XieCheng.receive" + tickets.toString());
    }
}