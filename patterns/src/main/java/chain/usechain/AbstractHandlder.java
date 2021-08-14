package chain.usechain;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AbstractHandlder
 * @Description
 * @date 2021/8/14 16:20
 */
public abstract class AbstractHandlder<T> {
    // 定义下一个节点解析器
    private AbstractHandlder<T> nextHandler;

    // 用来设置下一个节点解析器
    public void addNextHandler(AbstractHandlder<T> nextHandler) {
        this.nextHandler = nextHandler;
    }

    // 用来判断是否能处理T(交给子类去处理),其实就是根据文件类型判断
    protected abstract boolean preHandle(T t);

    // 处理 T(交给子类去处理),这就是具体的解析器
    protected abstract void onHandle(T t);

    // 处理的判断逻辑, 相当于判断的模板
    public void doHandle(T t) {
        // 先判断是否能处理,能处理则处理,根据文件类型判断能否处理
        if (preHandle(t)) {
            // 判断能处理的话,就调用对应的文件解析器
            onHandle(t);
            // 处理了直接返回
            return;
        }
        // 不能处理,判断有没有下一个解析器,有的话则调用下一个解析器处理
        if (this.nextHandler != null) {
            this.nextHandler.doHandle(t);
        }
    }

}
