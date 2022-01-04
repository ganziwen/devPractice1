package com.development.mock.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.development.mock.model.MappingParamData;
import com.development.mock.model.MappingParamEntity;
import com.development.mock.model.MappingParamInfo;
import com.development.mock.model.MockContext;
import com.development.mock.util.YmlUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.tinylog.Logger;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockController
 * @Description
 * @date 2021/12/26 15:56
 */
@RestController
public class MockController {

    // private static final Logger logger = LoggerFactory.getLogger(MockController.class);

    // 这个地址是你自己电脑上的绝对路径
    private static final String MOCK_DATA_ROOT_PATH = "D:\\Learn\\JAVA\\TestDevelement\\devPractice\\mock-server\\src\\main\\resources\\mock_data\\";
    private static final String MOCK_DATA_ROOT_PATH_VIP = "D:\\gzw\\giteeCode\\devPractice\\mock-server\\src\\main\\resources\\mock_data";

    @Resource
    private HttpServletRequest request;

    @RequestMapping("/**")
    public String doMock() {
        /*
        1. 拿到 uri 、拿到参数、拿到请求的 ip 地址
        2. 到指定的 mock 数据目录下查找匹配到的 mock 数据
            2.1 解析文件，然后按照参数和 ip 去进行匹配，并且每个匹配节点都有权重，默认 = 1
            2.2 计算权重，大的那个返回
         */
        MockContext mockContext = MockContext.builder()
                .requestIp(request.getRemoteHost())
                .requestUri(request.getRequestURI())
                .requestParams(parseRequestParam())
                .build();
        Logger.info("do mock start ,context = {}", String.valueOf(mockContext));

        /*
        基于 uri 获取目录下的指定文件，并判断其属性，是目录还是文件
            如果是文件，直接返回了，如果是目录则需要解析匹配
         */
        String filePath = MOCK_DATA_ROOT_PATH_VIP + mockContext.getMockFileName();
        Logger.info("filePath = {}", filePath);
        File mockDataFile = new File(filePath);
        // 判断是文件还是路径
        if (mockDataFile.exists()) {
            Logger.info("文件/路径存在");
        } else {
            Logger.info("文件/路径不存在");
        }
        if (mockDataFile.isFile()) {
            Logger.info("{} is file", filePath);
            try {
                MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(YmlUtils.readForObject(filePath, MappingParamData.class));
                Logger.info("读取的mock文件信息为 {}", mappingParamInfo.toString());
                Logger.info("返回内容为{}", mappingParamInfo.getResponse());
                // 单文件就不用去匹配，直接拿文件返回就完事了,但是有个问题是单文件怎么知道要返回 yml 的文件呢，或者强行将路径最后拼接一个 yml 直接读取呢
                return mappingParamInfo.getResponse();
            } catch (Exception e) {
                throw new IllegalStateException(e);
                // e.printStackTrace();
            }

        } else if (mockDataFile.isDirectory()) {
            Logger.info("{} is dictionary", filePath);
            // 假设是路径，这时候就要去解析出所有文件，点然后计算匹配，计算权重，找到最大的，然后返回
            List<String> mockDataFileNames = FileUtil.listFileNames(filePath);
            mockContext.getRequestParams().entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.toList());
            int weightResult = 0;
            for (String mockDataFileName : mockDataFileNames) {
                // String path = filePath + "/" + mockDataFileName;
                String path = filePath + "/" + mockDataFileName;
                Logger.info("path = {}", path);
                MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(YmlUtils.readForObject(path, MappingParamData.class));
                Logger.info("读取的mock文件信息为 {}", mappingParamInfo.toString());
                // 因为这里是有多个文件，所以要开始做参数匹配了.这里目前做的还是精确匹配，未来肯定是要可以支持正则或者是什么其他的条件的，不然限定的太死没法玩
                List<MappingParamEntity> mappingParamEntities = mappingParamInfo.getMappingParams();
                for (MappingParamEntity mappingParamEntity : mappingParamEntities) {

                    Map<String, Object> mappingParam = mappingParamEntity.getMappingParam();
                    Integer weight = mappingParamEntity.getWeight();
                }
            }
            return "none";
        } else {
            Logger.info("{} is not file or dictionary", filePath);
            return "none";
        }

    }

    // 获取 get 方法的参数列表
    private Map<String, String> parseRequestParam() {
        return this.request.getParameterMap()
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> getFirstValue(entry.getValue())));
    }

    private String getFirstValue(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";

        }
        return strArr[0];
    }

}
