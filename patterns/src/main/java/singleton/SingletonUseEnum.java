package singleton;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SingletonUseEnum
 * @Description
 * @date 2021/7/25 17:02
 * 利用枚举值,枚举妥妥的是全局唯一,虚拟机层面的全局唯一
 */
public enum SingletonUseEnum {
    INSTANCE;

    public SingletonUseEnum test() {

        return INSTANCE;
    }
}
