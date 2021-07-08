package common;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/1-22:41
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("hello world");
    }
}

class People {
    public void eat() {

    }
}

class Stu extends People {
    @Override
    public void eat() {
        super.eat();
    }
}

class Tea extends People{
    @Override
    public void eat() {
        super.eat();
    }
}