package com.example.autoframework.util;

import cn.hutool.log.StaticLog;
import com.example.autoframework.model.DataEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName YmlUtils
 * @Description
 * @date 2021/12/18 11:08
 */
public final class YmlUtils {
    public YmlUtils() {

    }

    private static final ObjectMapper MAPPER;

    static {
        YAMLFactory yamlFactory = new YAMLFactory();
        YAMLMapper yamlMapper = new YAMLMapper(yamlFactory);
        // 解析出错直接报错
        MAPPER = yamlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    public static Map<String, String> readForMap(String path) {
        RequiredUtils.requireNotNullOrEmpty(path, "file path should not null or empty");
        InputStream inputStream = null;

        try {
            Map<String, String> map = Maps.newHashMap();
            inputStream = Resources.getResource(path).openStream();
            JsonNode jsonNode = MAPPER.readTree(inputStream);
            jsonNode.fields().forEachRemaining(e -> {
                map.put(e.getKey(), e.getValue().asText());
            });

            return map;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    /**
     * 读取文件，其实我觉得直接读出来的 json 全部转成 HashMap 即可
     *
     * @param path
     * @return
     */
    public static List<DataEntity> read(String path) {
        StaticLog.info("path=" + path);
        RequiredUtils.requireNotNullOrEmpty(path, "file path should not null or empty");
        InputStream inputStream = null;
        List<DataEntity> entities = Lists.newArrayList();
        try {
            inputStream = Resources.getResource(path).openStream();
            JsonNode jsonNode = MAPPER.readTree(inputStream);
            // 将 jsonNode 解析成为 DataEntity
            if (!jsonNode.isEmpty()) {
                if (jsonNode.isArray()) {
                    for (JsonNode node : jsonNode) {
                        DataEntity dataEntity = new DataEntity();
                        node.fields().forEachRemaining(json -> {
                            DataEntity.Entity entity = transToEntity(json);
                            dataEntity.addEntity(entity);
                        });
                        entities.add(dataEntity);
                    }
                } else if (jsonNode.isObject()) {
                    DataEntity dataEntity = new DataEntity();
                    jsonNode.fields().forEachRemaining(json -> {
                        DataEntity.Entity entity = transToEntity(json);
                        dataEntity.addEntity(entity);
                    });
                    entities.add(dataEntity);
                }
            } else {
                StaticLog.error("文件内容的 json 为空");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;

    }

    private static DataEntity.Entity transToEntity(Map.Entry<String, JsonNode> json) {

        JsonNode jsonValue = json.getValue();
        return DataEntity.Entity.of(jsonValue.isObject(), json.getKey(), jsonValue.toString());

    }

    @Test
    public void testRead() {
        List<DataEntity> dataEntities = read("data/testDriver.yml");
        dataEntities.forEach(e -> System.out.println(e.toString()));
    }
}
