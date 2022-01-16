package com.development.mock.observer;

import com.alibaba.fastjson.JSON;
import com.development.mock.model.MappingParamEntity;
import com.development.mock.model.MockContext;
import com.development.mock.model.MockDataEntity;
import com.development.mock.model.MockDataInfo;
import com.development.mock.util.JsonFactory;
import com.development.mock.util.YmlUtils;
import com.google.common.base.Strings;
import org.tinylog.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CalcWeightObserver
 * @Description
 * @date 2022/1/8 21:02
 */
public class CalcWeightObserver implements IObserver<MockContext> {
    @Override
    public void update(MockContext mockContext) {
        int weightResult = 0;
        String response = null;
        Long timeOut = null;
        String pentrateUrl = null;
        List<MockDataInfo> mockDataInfoList = mockContext.getMockDataInfoList();
        List<String> requestParamList = mockContext.getRequestParams().entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.toList());

        for (MockDataInfo mockData : mockDataInfoList) {
            int weightSum = 0;

            // 因为这里是有多个文件，所以要开始做参数匹配了.这里目前做的还是精确匹配，未来肯定是要可以支持正则或者是什么其他的条件的，不然限定的太死没法玩
            List<MappingParamEntity> mappingParamEntities = mockData.getMappingParams();
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
                response = mockData.getResponse();
                timeOut = mockData.getTimeOut();
                pentrateUrl = mockData.getPentrateUrl();
                weightResult = weightSum;

            } else {
                Logger.info("当前匹配的权重和为:{}", weightResult);
            }
        }
        if (Strings.isNullOrEmpty(response)) {
            Logger.info("mock not attach");
            throw new IllegalStateException("mock not attach");
        }
        mockContext.setFinalResponse(response);
        mockContext.setTimeOut(timeOut);
        mockContext.setPentrateUrl(pentrateUrl);
    }
}
