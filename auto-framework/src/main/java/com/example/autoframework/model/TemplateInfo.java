package com.example.autoframework.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TemplateInfo
 * @Description
 * @date 2021/11/27 18:57
 */
@Data
@Builder
public class TemplateInfo {
    // 文件名
    @NotBlank
    @NotNull
    @NotEmpty
    private String templateKey;
    // 文件名对应的内容
    private String templateValue;
}
