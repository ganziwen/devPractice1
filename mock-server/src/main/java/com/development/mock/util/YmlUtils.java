package com.development.mock.util;

import cn.hutool.core.io.IoUtil;
import com.development.mock.model.MockDataEntity;
import com.development.mock.model.MockDataInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName YmlUtils
 * @Description ArrayList<Object>
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
     * 只能读取单组配置
     *
     * @param absolutePath
     * @return
     */
    public static Object ymlLoadSingleToJson(String absolutePath) {
        //初始化Yaml解析器
        Yaml yaml = new Yaml();
        File f = new File(absolutePath);
        //读入文件
        Object result = null;
        try {
            result = yaml.load(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取 yml 内的多组配置
     *
     * @param absolutePath
     * @return
     */
    public static ArrayList<Object> ymlLoadAllToJson(String absolutePath) {
        Yaml yaml = new Yaml();
        File f = new File(absolutePath);
        ArrayList<Object> lists = Lists.newArrayList();
        Iterable<Object> result = null;
        try {
            result = yaml.loadAll(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (result != null) {
            for (Object obj : result) {
                lists.add(obj);
            }
        } else {
            throw new IllegalStateException("file " + absolutePath + "is empty");
        }
        return lists;
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
        // MappingParamData mappingParamData = readForObject("D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\create_account\\account_gzw.yml", MappingParamData.class);
        // MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(mappingParamData);
        // System.out.println("mappingParamData = " + mappingParamData);
        // System.out.println("mappingParamInfo = " + mappingParamInfo);

        // MockDataEntity mockDataEntityVip = readForObject("D:\\gzw\\giteeCode\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\create_account\\account_gzw.yml", MockDataEntity.class);
        String mockDataEntityVip2 = readForObject("D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\create_order", String.class);
        System.out.println("mockDataEntityVip2 = " + mockDataEntityVip2);

        // MockDataInfo mockDataInfoVip = MockDataInfo.fromMappingParamData(mockDataEntityVip);
        // System.out.println("mappingParamData = " + mockDataEntityVip);
        // System.out.println("mappingParamInfo = " + mockDataInfoVip);
    }


}
