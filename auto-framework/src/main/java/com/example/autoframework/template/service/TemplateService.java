package com.example.autoframework.template.service;

import com.example.autoframework.model.TemplateInfo;
import com.example.autoframework.template.factory.TemplateFactory;
import com.example.autoframework.util.RequiredUtils;
import org.springframework.util.StringUtils;

import java.util.Formatter;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TemplateService
 * @Description
 * @date 2021/11/27 20:52
 */
public class TemplateService {
    public TemplateInfo getTemplateName(String templateName) {
        RequiredUtils.requireNotNullOrEmpty(templateName, "template name should not be null or empty");
        return TemplateFactory.of().getTemplateByName(templateName);

    }

    public String replaceTemplate(String templateKey, Map<String, String> mapping) {
        TemplateInfo templateInfo = getTemplateName(templateKey);
        String templateValue = templateInfo.getTemplateValue();

        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            templateValue = StringUtils.replace(templateValue, createTemplateKey(entry.getKey()), entry.getValue());
        }

        return templateValue;
    }

    public String createTemplateKey(String key) {
        return new Formatter().format("${%s}", key).toString();
    }


}
