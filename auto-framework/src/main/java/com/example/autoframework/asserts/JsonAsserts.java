package com.example.autoframework.asserts;

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
            JsonNode rootNode = MAPPER.readTree(this.json);
            JsonNode valueNode = rootNode.requiredAt(path);
            T actuallyValue = function.apply(valueNode);
            if (!expectValue.equals(actuallyValue)) {
                throw new IllegalStateException("not equals.");
            }
            return this;
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
