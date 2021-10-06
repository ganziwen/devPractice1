package framework.chain;

import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AbstractHandler
 * @Description
 * @date 2021/10/6 13:56
 */
public abstract class AbstractHandler<Response, Context> {
    private AbstractHandler<Response, Context> nextHandler;

    protected abstract boolean preHandle(Context context);

    protected abstract Response onHandle(Context context);

    public Response doHandle(Context context) {
        if (preHandle(context)) {
            return onHandle(context);

        }
        if (!Objects.isNull(this.nextHandler)) {
            return this.nextHandler.doHandle(context);
        }
        throw new IllegalArgumentException("unknown handler to handle");
    }

    public void setNextHandler(AbstractHandler<Response, Context> nextHandler) {
        this.nextHandler = nextHandler;
    }


}
