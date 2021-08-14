package chain.usechain;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AbstractHandlder
 * @Description
 * @date 2021/8/14 16:20
 */
public abstract class AbstractHandlder<T> {
    private AbstractHandlder<T> nextHandler;

    public void addNextHandler(AbstractHandlder<T> nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract boolean preHandle(T t);

    protected abstract void onHandle(T t);

    public void doHandle(T t) {
        if (preHandle(t)) {
            onHandle(t);
            return;
        }
        if (this.nextHandler != null) {
            this.nextHandler.doHandle(t);
        }
    }

}
