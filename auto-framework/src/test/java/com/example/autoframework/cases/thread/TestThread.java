package com.example.autoframework.cases.thread;

import com.example.autoframework.annotation.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;

import static com.example.autoframework.enums.Project.LEVEL_NORMAL;
import static com.example.autoframework.enums.Project.LEVEL_RED_LINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TestAccount
 * @Description
 * @date 2021/11/14 18:11
 */
public class TestThread {

    @AutoTest
    @DisplayName("测试支付-正常-3")
    @CaseTitle("正常用例-3") // 给 case 加标题便于后续查找和区分,必填
    @CaseDesc(desc = "测试支付", owner = "Ganziwen")
    @CheckPoint("检查点1")// 为 case 检查点,必填
    @CheckPoint("检查点2")// 为 case 检查点,必填
    @CaseTag(key = "project", val = "头条")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseGroup(team = "test_team", group = "test_group")
    @RepeatedTest(10) //10为当前用例执行的次数
    @Execution(CONCURRENT)  //CONCURRENT表示支持多线程
    public void testNormal1() {
        System.out.println("TestAccount.testNormal1");
        // assertThat(1).isEqualTo(0);
    }


}
