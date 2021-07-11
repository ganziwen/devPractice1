package exception;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName Demo3
 * @Description
 * @date 2021/7/11 16:02
 */
public class Demo3 {
    public static void main(String[] args) {

        try (Foo1 foo1 = new Foo1()) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Foo1 implements Closeable {
        @Override
        public void close() throws IOException {

        }
    }

}
