package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName RowEntity
 * @Description
 * @date 2021/12/22 17:19
 */
@Data
@Builder
public class RowEntity {
    private List<FieldEntity> fields;
}
