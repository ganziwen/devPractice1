package generic;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/6-19:12
 */
public class ApachePair {
    public static void main(String[] args) {
        Pair<String, String> pair1 = Pair.of("abxc", "jjjj");
//        Pair<String, Integer> pair2 = Pair.of("lalala", 12345);
        Pair<String, Integer> pair2 = Pair.of("lalala", 12345);

        // getLeft() = getKey(); getRight() = getValue()
        System.out.println("pair1.getLeft() = " + pair1.getLeft());
        System.out.println("pair2.getRight() = " + pair2.getRight());
    }
}
