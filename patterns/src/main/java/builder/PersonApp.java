package builder;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/26-18:48
 * 建造者设计模式
 */
public class PersonApp {
    public static void main(String[] args) {

        testPersonWithLombok();
    }

    public static void testPersonWithLombok() {
        Person person = Person.builder().age(18).height(180).name("小明").build();
        System.out.println("person = " + person);
    }
}
