package framework.chain;

import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AbstractHandler
 * @Description
 * @date 2021/10/6 13:56
 */
public abstract class AbstractHandler<Context> {
    private AbstractHandler<Context> nextHandler;

    protected abstract boolean preHandle(Context context);

    protected abstract void onHandle(Context context);

    public void doHandle(Context context) {
        if (preHandle(context)) {
            onHandle(context);
            return;

        }
        if (!Objects.isNull(this.nextHandler)) {
            this.nextHandler.doHandle(context);
            return;
        }
        throw new IllegalArgumentException("unknown handler to handle");
    }

    public void setNextHandler(AbstractHandler<Context> nextHandler) {
        this.nextHandler = nextHandler;
    }


}
