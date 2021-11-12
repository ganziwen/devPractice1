package annotation;

import annotation.annoer.JianCha;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/12-16:05
 */
public class NoBug {
    @JianCha
    public void test1() {
        System.out.println("1234567890");
        assertThat("1234567890").isEqualTo("1234567890");
    }

    @JianCha
    public void test2() {
        System.out.println("1+1=" + 1 + 1);
        assertThat(1 + 1).isEqualTo(2);
    }

    @JianCha
    public void test3() {
        System.out.println("1-1=" + (1 - 1));
        assertThat(1 - 1).isEqualTo(0);

    }

    @JianCha
    public void test4() {
        System.out.println("3 x 5=" + 3 * 5);
        assertThat(3 * 5).isEqualTo(15);


    }

    @JianCha
    public void test5() {
        System.out.println("3 x 5=" + 16);
        assertThat(3 * 5).isEqualTo(16);

    }

    public void testNoBug() {
        System.out.println("我的代码没有 bug");
    }
}
