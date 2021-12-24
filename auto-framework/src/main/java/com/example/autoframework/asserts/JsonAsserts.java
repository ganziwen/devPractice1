package com.example.autoframework.asserts;

import ch.qos.logback.core.rolling.DefaultTimeBasedFileNamingAndTriggeringPolicy;
import com.alibaba.druid.util.StringUtils;
import com.dingtalk.api.response.OapiReportTemplateListbyuseridResponse;
import com.example.autoframework.model.RequestType;
import com.example.autoframework.util.RequiredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.internal.toplink.embedded.websocket.util.StringUtil;
import org.assertj.core.api.Assertions;
import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.function.Function;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName JsonAssert
 * @Description
 * @date 2021/12/23 17:48
 */
public final class JsonAsserts {
    private String json;
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    public JsonAsserts(String json) {
        this.json = json;
    }

    public static JsonAsserts assertThat(String json) {
        RequiredUtils.requireNotNullOrEmpty(json, "should not be null or empty");
        return new JsonAsserts(json);
    }

    private JsonAsserts isEquals(String path, String expectValue) {
        return isEquals(path, expectValue, JsonNode::asText);
    }

    private JsonAsserts isEquals(String path, Integer expectValue) {
        return isEquals(path, expectValue, JsonNode::asInt);
    }

    private <T> JsonAsserts isEquals(String path, T expectValue, Function<JsonNode, T> function) {
        try {
            // 基于 objectmapper 来解析 json ，生成树，得到根节点
            JsonNode rootNode = MAPPER.readTree(this.json);
            // 拿着根节点去获取获取指定路径下的节点
            JsonNode valueNode = rootNode.requiredAt(path);
            // 遇到不同的 expectValue 类型，我们要有不同的判断逻辑，这里是变化的，抽象的话立即想到接口、抽象类
            // 两个变量:基于 T 返回 asXXX，Function<JsonNode, T> 就是接受 JsonNode 的，返回个 T 类型的
            // if (expectValue instanceof Integer) {
            //     return valueNode.asInt();
            // }

            T actuallyValue = function.apply(valueNode);
            if (!expectValue.equals(actuallyValue)) {
                throw new IllegalStateException("not equals.");
            }
            return this;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }


    public void test1() {


    }
}
