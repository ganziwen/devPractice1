package framework.observe;

import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ObserverManager
 * @Description
 * @date 2021/10/10 11:40
 */
public class ObserverManager {
    private List<IObserve<Context>> observeList;

    private ObserverManager() {
        this.observeList = new ArrayList<>();
        this.observeList.add(new DiffObserver());
        this.observeList.add(new SyncObserver());
    }

    private static class ClassHolder {
        private static final ObserverManager HOLDER = new ObserverManager();
    }

    public static ObserverManager of() {
        return ClassHolder.HOLDER;
    }

    public void update(Context context) {
        this.observeList.forEach(observe -> {
            if (observe.preUpdate(context)) {
                observe.update(context);
            }
        });
    }
}
