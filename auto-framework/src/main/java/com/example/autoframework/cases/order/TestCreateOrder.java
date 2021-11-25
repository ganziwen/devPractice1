package com.example.autoframework.cases.order;

import com.example.autoframework.annotation.*;
import org.junit.jupiter.api.DisplayName;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TestCreateOrder
 * @Description
 * @date 2021/11/13 15:57
 */
public class TestCreateOrder {

    @AutoTest
    @DisplayName("测试支付-正常-1")
    @CaseTitle("正常用例-1") // 给 case 加标题便于后续查找和区分,必填
    @CaseDesc(desc = "测试支付", owner = "Ganziwen")
    @DingTalkAlarm(token = "testToken") // 用于报警处理
    @CheckPoint("检查点1")// 为 case 检查点,必填
    @CheckPoint("检查点2")// 为 case 检查点,必填
    @CaseTag(key = "project", val = "meituan")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "module", val = "Order")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "level", val = "redline")// 为 case 添加标签后方便后续运行时做筛选,必填
    public void testNormal1() {
        System.out.println("TestCreateOrder.testNormal1");
    }

    @AutoTest
    @DisplayName("测试支付-正常-2")
    @CaseTitle("正常用例-2") // 给 case 加标题便于后续查找和区分,必填
    @CaseDesc(desc = "测试支付", owner = "Ganziwen")
    @DingTalkAlarm(token = "testToken") // 用于报警处理
    @CheckPoint("检查点1")// 为 case 检查点,必填
    @CheckPoint("检查点2")// 为 case 检查点,必填
    @CaseTag(key = "project", val = "头条")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "module", val = "Order")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "level", val = "normal")// 为 case 添加标签后方便后续运行时做筛选,必填
    public void testNormal2() {
        System.out.println("TestCreateOrder.testNormal2");
    }

    @AutoTest
    @DisplayName("测试支付-正常-3")
    @CaseTitle("正常用例-3") // 给 case 加标题便于后续查找和区分,必填
    @CaseDesc(desc = "测试支付", owner = "Ganziwen")
    @DingTalkAlarm(token = "testToken") // 用于报警处理
    @CheckPoint("检查点1")// 为 case 检查点,必填
    @CheckPoint("检查点2")// 为 case 检查点,必填
    @CaseTag(key = "project", val = "头条")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "module", val = "Order")// 为 case 添加标签后方便后续运行时做筛选,必填
    @CaseTag(key = "level", val = "redline")// 为 case 添加标签后方便后续运行时做筛选,必填
    public void testNormal3() {
        System.out.println("TestCreateOrder.testNormal3");
    }
}
