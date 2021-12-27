package com.example.autoframework.cases.pay;

import com.alibaba.fastjson.JSONPath;
import com.example.autoframework.annotation.*;

import com.example.autoframework.http.HttpFacade;
import com.example.autoframework.model.HttpRequest;
import com.example.autoframework.model.HttpResponse;
import com.example.autoframework.model.NewOrder;
import com.example.autoframework.paramholder.AutoParamsHolder;
import com.google.common.collect.Maps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TestApiPay
 * @Description
 * @date 2021/12/23 12:02
 */
public class TestApiPay2 {


    @AutoTest
    @DisplayName("测试支付的流程")
    @CaseTitle("支付宝订单创建接口")
    @CaseDesc(desc = "主流程用例，正常走通全链路", owner = "Ganziwen")
    @CheckPoint("订单落地信息准确")
    @CheckPoint("订单状态机 == INIT")
    @CaseTag(key = "bussiness", val = "pay")
    @CaseTag(key = "channel", val = "apipay")
    public void testCreateOrder() {
        // 1. 清理环境，准备环境
        // 拿到具体的 profile ，也知道模块，所以就去 check 在指定 profile 环境中的具体模块的保活接口
        // EnvCheck.check("pay","order","getway");

        // 2. 准备数据


        // 3. 构造请求
        Map<String, String> mapping = Maps.newHashMap();
        mapping.put("", "");
        NewOrder holder = AutoParamsHolder.getForTestCreateOrder().createBean(NewOrder.class, mapping);
        System.out.println("holder = " + holder);
        HttpResponse httpResponse = HttpFacade.doRequest(HttpRequest
                .builder()
                .data(holder)
                .build());
        // 4. 向接口发请求

        String body = httpResponse.getBody();

        // 5. 拿到接口 res 断言
        Assertions.assertThat(JSONPath.read(body, "$..msg")).isEqualTo("成功");
        // 6. 断言 db 相关逻辑
        // 7. 清理测试数据

    }
}
