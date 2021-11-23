package com.example.autoframework.test.demo1;

import com.example.autoframework.annotation.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/11-17:14
 */
public class TestDemo1 {

    @Test
    @DisplayName("运行时的展示名称testNormal")
    @Tag("normal")
    @Tag("P0")
    @Timeout(1000)
    @RepeatedTest(3)
    @Order(2)
    public void testNormal() {
        assertThat(1).isEqualTo(1);
    }


    @Test
    @DisplayName("运行时的展示名称testNormal2")
    @Tag("normal")
    @Tag("P0")
    @Timeout(1000)
    @RepeatedTest(3)
    @Order(1)
    public void testNormal2() {
        assertThat(1).isEqualTo(1);
    }


    @AutoTest
    @CaseTitle("测试用例") // 给 case 加标题便于后续查找和区分，必填
    @CaseDesc(desc = "111", owner = "441")
    @DingTalkAlarm(token = "testToken") // 用于报警处理
    @CheckPoint("检查点")// 为 case 检查点，必填
    @CheckPoint("3232")// 为 case 检查点，必填
    @CaseTag(key = "project", val = "meituan")// 为 case 添加标签后方便后续运行时做筛选，必填
    // @CaseTag(project = "project_name", module = "pay")// 为 case 添加标签后方便后续运行时做筛选，必填
    // @CaseTag(team = "vip", group = "create")// 为 case 添加标签后方便后续运行时做筛选，必填
    // @CaseTag(key = "level", val = "nomal")// 为 case 添加标签后方便后续运行时做筛选，必填
    // @CaseProject(project = "", module = "")
    // @CaseGroup(team = "", group = "") // 分组
    public void test1() {

        System.out.println("TestDemo1.test1");
        /*
         * 预期的 case 编写样式
         * 1. 要扩展出自己的标识
         * TODO  2. 扩展出去之后要用于做什么？给到平台，做 case 管理。做 case 评审时使用
         * 3. 有必填项，也有非必填，那必填项该如何控制必填呢？不管是否为必填项，必要的参数是需要做校验处理的
         */
    }
}
