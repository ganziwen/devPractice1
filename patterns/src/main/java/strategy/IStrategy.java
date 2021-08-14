package strategy;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName IParseStrategy
 * @Description
 * @date 2021/8/14 23:34
 */
public interface IStrategy<T> {
    void update(T t);
}
