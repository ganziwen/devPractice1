package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FieldEntity
 * @Description
 * @date 2021/12/22 17:18
 */
@Data
@Builder
public class FieldEntity {
    private String fieldName;
    private Object fieldValue;
}
