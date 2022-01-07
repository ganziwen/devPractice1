package com.development.mock.service.impl;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.development.mock.chain.ChainManager;
import com.development.mock.model.MappingParamData;
import com.development.mock.model.MappingParamEntity;
import com.development.mock.model.MappingParamInfo;
import com.development.mock.model.MockContext;
import com.development.mock.service.MockService;
import com.development.mock.util.ArrayUtils;
import com.development.mock.util.JsonFactory;
import com.development.mock.util.YmlUtils;
import org.springframework.stereotype.Service;
import org.testng.util.Strings;
import org.tinylog.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.development.mock.constants.PathConstant.MOCK_DATA_ROOT_PATH_VIP;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/6-11:51
 */
@Service
public class MockServiceImpl implements MockService {
    @Override
    public String doMock(MockContext mockContext) {
        return ChainManager.newInstance.doMapping(mockContext);
    }


    /**
     * 还未进行优化的 mock 方法
     *
     * @param mockContext
     * @return
     */
    public String doMockBak(MockContext mockContext) {



        /*
        基于 uri 获取目录下的指定文件，并判断其属性，是目录还是文件
            如果是文件，直接返回了，如果是目录则需要解析匹配
         */
        // 这个地址是你自己电脑上的绝对路径
        String filePath = MOCK_DATA_ROOT_PATH_VIP + mockContext.getMockFileName();
        File mockDataFile = new File(filePath);
        // 判断是文件还是路径

        if (!mockDataFile.exists()) {
            Logger.info("文件/路径:{}不存在", filePath);
        }
        if (mockDataFile.isFile()) {
            Logger.info("{} is file", filePath);
            try {
                MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(YmlUtils.readForObject(filePath, MappingParamData.class));
                Logger.info("读取的mock文件信息为 {}", JsonFactory.objectToJson(mappingParamInfo));
                Logger.info("返回内容为{}", JsonFactory.objectToJson(mappingParamInfo));
                // 单文件就不用去匹配，直接拿文件返回就完事了,但是有个问题是单文件怎么知道要返回 yml 的文件呢，或者强行将路径最后拼接一个 yml 直接读取呢
                return mappingParamInfo.getResponse();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

        } else if (mockDataFile.isDirectory()) {
            Logger.info("{} is dictionary", filePath);
            // 假设是路径，这时候就要去解析出所有文件，点然后计算匹配，计算权重，找到最大的，然后返回
            List<String> mockDataFileNames = FileUtil.listFileNames(filePath);
            List<String> requestParamList = mockContext.getRequestParams().entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.toList());
            Logger.info("传进来的匹配条件:{}", requestParamList.toString());
            int weightResult = 0;
            String response = null;
            // 多个文件循环遍历，并且计算权重
            for (String mockDataFileName : mockDataFileNames) {

                int weightSum = 0;
                // String path = filePath + "/" + mockDataFileName;
                String path = filePath + "/" + mockDataFileName;
                Logger.info("path = {}", path);
                MappingParamInfo mappingParamInfo = MappingParamInfo.fromMappingParamData(YmlUtils.readForObject(path, MappingParamData.class));
                // 因为这里是有多个文件，所以要开始做参数匹配了.这里目前做的还是精确匹配，未来肯定是要可以支持正则或者是什么其他的条件的，不然限定的太死没法玩
                List<MappingParamEntity> mappingParamEntities = mappingParamInfo.getMappingParams();

                // 这里要匹配一下 host 是否命中，命中再往下，没命中就 continue，或者在 weight 内我们再设置一个权重，再加到 param 的权重内进行全局加和比较
                if (!mappingParamInfo.getMappingHost().equals(mockContext.getRequestIp())) {
                    continue;
                } else {
                    Logger.info("ip 命中");
                }

                // 单个文件内的 mappingParams 进行循环遍历，所有参数进行匹配，累加权重进行计算
                for (MappingParamEntity mappingParamEntity : mappingParamEntities) {

                    Map<String, Object> mappingParam = mappingParamEntity.getMappingParam();
                    String param = mappingParam.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).findFirst().get();
                    // 请求的参数内，包含了我们写的参数，说明命中了
                    // 这里的判断是将 请求的 key=value 拼接，对文件内的 key=value 进行字符串比对，其实用 map 比对比较好，因为有的 value 值并不是 string ，可能还有一些 json 之类的
                    if (requestParamList.contains(param)) {
                        // 捞出权重
                        Integer weight = mappingParamEntity.getWeight();
                        weightSum += weight;

                    }
                }
                // 单文件内的权重值 >  当前总权重的话，则将 map 住的信息赋值给 response
                if (weightSum > weightResult) {
                    Logger.info("命中的mock文件信息为:{}", JsonFactory.objectToJson(mappingParamInfo));

                    weightResult = weightSum;
                    response = mappingParamInfo.getResponse();
                } else {
                    Logger.info("当前匹配的权重和为:{}", weightResult);
                }

            }
            // 最后判断下返回的 response 为空则返回没命中的信息，不为空则返回命中的内容

            if (Strings.isNullOrEmpty(response)) {

                return "mock not attach";
            } else {
                Logger.info("返回的 response = {}", JsonFactory.objectToJson(JSON.parseObject(response)));
                return response;
            }


        } else {
            Logger.info("{} is not file or dictionary", filePath);
            return "none";
        }

    }
}
