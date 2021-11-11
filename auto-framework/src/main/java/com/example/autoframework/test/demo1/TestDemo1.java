package com.example.autoframework.test.demo1;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/11-17:14
 */
public class TestDemo1 {

    @Test
    @DisplayName("运行时的展示名称")
    @Tag("normal")
    @Tag("P0")
    @Timeout(1000)
    @RepeatedTest(3)
    public void testNormal() {
        assertThat(1).isEqualTo(1);
    }
}
