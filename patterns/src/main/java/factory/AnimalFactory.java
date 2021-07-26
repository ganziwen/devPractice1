package factory;

import java.util.Optional;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-16:32
 * 简单版本,只用单例来整
 */
public class AnimalFactory {
    private AnimalFactory() {
    }

    private static class ClassHolder {
        private static final AnimalFactory INSTANCE = new AnimalFactory();
    }

    public static AnimalFactory of() {
        return ClassHolder.INSTANCE;
    }

    /**
     * 缺点:假设类型增多了还得自己手动加,扩展性不强,有没有什么更简单的方式?
     *
     * @param description
     * @return
     */
    public Animal createAnimalInstance(String description) {
        if (description.equals("dog")) {
            return new Dog();
        }
        if (description.equals("cat")) {
            return new Cat();
        }
        throw new IllegalArgumentException(String.format("unknow description:%s", description));
    }

    /**
     * 加入反射
     *
     * @param className
     * @return
     */
    public Optional<Animal> createAnimalInstanceWithReflect(String className) {
        try {
            Class<?> aClass = Class.forName(className);
            return Optional.of((Animal) aClass.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
