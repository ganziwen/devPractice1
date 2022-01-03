package com.development.mock.model;

import lombok.Builder;
import lombok.Data;
import org.omg.CORBA.INTERNAL;

import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName MappingParamEntity
 * @Description
 * @date 2022/1/3 11:24
 */
@Builder
@Data
public class MappingParamEntity {
    private Map<String, Object> mappingParam;
    private Integer weight;
}
