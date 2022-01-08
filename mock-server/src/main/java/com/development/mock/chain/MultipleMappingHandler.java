package com.development.mock.chain;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.development.mock.model.MappingParamEntity;
import com.development.mock.model.MockContext;
import com.development.mock.model.MockDataEntity;
import com.development.mock.model.MockDataInfo;
import com.development.mock.observer.ObserverManager;
import com.development.mock.util.JsonFactory;
import com.development.mock.util.YmlUtils;
import com.google.common.base.Strings;
import org.apache.commons.lang3.tuple.Pair;
import org.tinylog.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/7-11:50
 * 多个的匹配
 */
public class MultipleMappingHandler extends AbstractMockHandler<MockContext, String> {
    @Override
    protected boolean preHandle(MockContext mockContext) {
        Pair<String, File> fileStringPair = judgeFile(mockContext);
        if (fileStringPair.getRight().isDirectory()) {
            Logger.info("{} is dictionary", fileStringPair.getRight());
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected String onHandle(MockContext mockContext) {
        /*
        这里又要加上设计模式了，这里是多文件匹配
        1. 加载本地的 mock 文件，将其数据转化成我们需要的数据对象
        2. 基于请求的参数集合来计算第一步中的所有匹配的数据权重加和
        3. 处理数据……
         */
        return ObserverManager.newInstance.getMockData(mockContext);


        //     Pair<String, File> fileStringPair = judgeFile(mockContext);
        //     String filePath = fileStringPair.getLeft();
        //     List<String> mockDataFileNames = FileUtil.listFileNames(filePath);
        //     List<String> requestParamList = mockContext.getRequestParams().entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.toList());
        //     Logger.info("传进来的匹配条件:{}", requestParamList.toString());
        //     int weightResult = 0;
        //     String response = null;
        //     // 多个文件循环遍历，并且计算权重
        //     for (String mockDataFileName : mockDataFileNames) {
        //
        //         int weightSum = 0;
        //         // String path = filePath + "/" + mockDataFileName;
        //         String path = filePath + "/" + mockDataFileName;
        //         Logger.info("path = {}", path);
        //         MockDataInfo mappingParamInfo = MockDataInfo.fromMappingParamData(YmlUtils.readForObject(path, MockDataEntity.class));
        //         // 因为这里是有多个文件，所以要开始做参数匹配了.这里目前做的还是精确匹配，未来肯定是要可以支持正则或者是什么其他的条件的，不然限定的太死没法玩
        //         List<MappingParamEntity> mappingParamEntities = mappingParamInfo.getMappingParams();
        //
        //         // 这里要匹配一下 host 是否命中，命中再往下，没命中就 continue，或者在 weight 内我们再设置一个权重，再加到 param 的权重内进行全局加和比较
        //         if (!mappingParamInfo.getMappingHost().equals(mockContext.getRequestIp())) {
        //             continue;
        //         } else {
        //             Logger.info("ip 命中");
        //         }
        //
        //         // 单个文件内的 mappingParams 进行循环遍历，所有参数进行匹配，累加权重进行计算
        //         for (MappingParamEntity mappingParamEntity : mappingParamEntities) {
        //
        //             Map<String, Object> mappingParam = mappingParamEntity.getMappingParam();
        //             String param = mappingParam.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).findFirst().get();
        //             // 请求的参数内，包含了我们写的参数，说明命中了
        //             // 这里的判断是将 请求的 key=value 拼接，对文件内的 key=value 进行字符串比对，其实用 map 比对比较好，因为有的 value 值并不是 string ，可能还有一些 json 之类的
        //             if (requestParamList.contains(param)) {
        //                 // 捞出权重
        //                 Integer weight = mappingParamEntity.getWeight();
        //                 weightSum += weight;
        //
        //             }
        //         }
        //         // 单文件内的权重值 >  当前总权重的话，则将 map 住的信息赋值给 response
        //         if (weightSum > weightResult) {
        //             Logger.info("命中的mock文件信息为:{}", JsonFactory.objectToJson(mappingParamInfo));
        //
        //             weightResult = weightSum;
        //             response = mappingParamInfo.getResponse();
        //         } else {
        //             Logger.info("当前匹配的权重和为:{}", weightResult);
        //         }
        //
        //     }
        //     // 最后判断下返回的 response 为空则返回没命中的信息，不为空则返回命中的内容
        //
        //     if (Strings.isNullOrEmpty(response)) {
        //
        //         return "mock not attach";
        //     } else {
        //         Logger.info("返回的 response = {}", JsonFactory.objectToJson(JSON.parseObject(response)));
        //         return response;
        //     }

    }
}
