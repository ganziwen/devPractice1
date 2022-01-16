package com.development.mock.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MockDataInfo
 * @Description
 * @date 2022/1/3 15:29
 */
@Data
@Builder
public class MockDataInfo {
    private String mappingHost;
    private List<MappingParamEntity> mappingParams;
    private String response;
    private Long timeOut;


    /**
     * 这里是将 weight 提取出来，其实按我的理解，这个 weight 可以跟 host 一样，没必要放进 param 里面，也就没必要单独处理出来
     *
     * @param mockDataEntity
     * @return
     */
    public static MockDataInfo fromMappingParamData(MockDataEntity mockDataEntity) {

        // 从 mappingParamData 内取出 weight 返回 list
        List<MappingParamEntity> paramEntities = mockDataEntity.getMappingParams().stream()
                .map(m -> {
                            Object weightVal = m.remove("weight");
                            // 获取出来的 weight 不能为 null ，否则默认设置为 1
                            Integer weight = Objects.isNull(weightVal) ? 1 : Integer.parseInt(String.valueOf(weightVal));
                            return MappingParamEntity.builder().weight(weight).mappingParam(m).build();
                        }

                ).collect(Collectors.toList());
        return MockDataInfo.builder()
                .mappingHost(mockDataEntity.getMappingHost())
                .response(mockDataEntity.getResponse())
                .mappingParams(paramEntities)
                .timeOut(mockDataEntity.getTimeOut())
                .build();
    }

}
