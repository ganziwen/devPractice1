package com.example.autoframework.cases.alarm;

import com.example.autoframework.alarm.callback.DefaultAlarmCallback;
import com.example.autoframework.annotation.*;
import org.junit.jupiter.api.DisplayName;

import static com.example.autoframework.enums.Project.DING_DING_TOKEN;
import static com.example.autoframework.enums.Project.LEVEL_NORMAL;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/11/25-16:24
 */
public class TestAlarm {
    @AutoTest
    @DisplayName("测试支付-正常-3")
    @CaseTitle("测试报警1") // 给 case 加标题便于后续查找和区分,必填
    @CaseDesc(desc = "测试报警，此方法会触发报警", owner = "Ganziwen")
    @DingTalkAlarm(token = DING_DING_TOKEN, callback = DefaultAlarmCallback.class) // 用于报警处理
    @CheckPoint("报警检查点1")// 为 case 检查点,必填
    @CheckPoint("报警检查点2")// 为 case 检查点,必填
    @CaseTag(key = "project", val = "头条")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "module", val = "Order")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "level", val = LEVEL_NORMAL)// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseGroup(team = "test_team", group = "test_group")
    public void testAlarm1() {
        System.out.println("TestAlarm.testAlarm1");
        assertThat(1).isEqualTo(0);
    }


    @AutoTest
    @DisplayName("测试支付-正常-3")
    @CaseTitle("测试报警2") // 给 case 加标题便于后续查找和区分,必填
    @CaseDesc(desc = "测试报警，此方法会触发报警", owner = "Ganziwen")
    @DingTalkAlarm(token = DING_DING_TOKEN, callback = DefaultAlarmCallback.class) // 用于报警处理
    @CheckPoint("报警检查点1")// 为 case 检查点,必填
    @CheckPoint("报警检查点2")// 为 case 检查点,必填
    @CaseTag(key = "project", val = "头条")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "module", val = "Order")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "level", val = LEVEL_NORMAL)// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseGroup(team = "test_team", group = "test_group")
    public void testAlarm2() {
        System.out.println("TestAlarm.testAlarm2");
        assertThat(1).isEqualTo(0);
    }
}
