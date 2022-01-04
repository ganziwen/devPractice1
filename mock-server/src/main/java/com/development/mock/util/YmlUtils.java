package com.development.mock.util;

import cn.hutool.core.io.IoUtil;
import com.development.mock.model.MappingParamData;
import com.development.mock.model.MappingParamInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.collect.Maps;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    /**
     * 根据绝对路径返回 yml 的 map
     *
     * @param absolutePath
     * @return
     */
    public static Map<String, Object> readForMap(String absolutePath) {

        InputStream inputStream = null;

        try {
            Map<String, Object> map = Maps.newHashMap();
            // inputStream = Resources.getResource(path).openStream();
            inputStream = IoUtil.toStream(new File(absolutePath));
            JsonNode jsonNode = MAPPER.readTree(inputStream);
            jsonNode.fields().forEachRemaining(e -> {
                map.put(e.getKey(), e.getValue());
            });

            return map;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T readForObject(String path, Class<T> clazz) {

        InputStream inputStream = null;
        try {
            // inputStream = Resources.getResource(path).openStream();
            inputStream = IoUtil.toStream(new File(path));
            return MAPPER.readValue(inputStream, clazz);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public static void testReadForObject() {
        // MappingParamData mappingParamData = readForObject("D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\create_account\\account_1.yml", MappingParamData.class);
        // MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(mappingParamData);
        // System.out.println("mappingParamData = " + mappingParamData);
        // System.out.println("mappingParamInfo = " + mappingParamInfo);

        MappingParamData mappingParamDataVip = readForObject("D:\\gzw\\giteeCode\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\create_account\\account_1.yml", MappingParamData.class);
        MappingParamInfo mappingParamInfoVip = MappingParamInfo.fromMappingParamData(mappingParamDataVip);
        System.out.println("mappingParamData = " + mappingParamDataVip);
        System.out.println("mappingParamInfo = " + mappingParamInfoVip);
    }


}
