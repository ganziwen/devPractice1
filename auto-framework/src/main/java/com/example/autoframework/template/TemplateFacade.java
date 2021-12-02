package com.example.autoframework.template;

import com.example.autoframework.model.TemplateInfo;
import com.example.autoframework.template.service.TemplateService;

import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TemplateFacade
 * @Description
 * @date 2021/11/27 18:50
 */
public class TemplateFacade {
    public TemplateFacade() {

    }

    public static TemplateInfo getTemplateByName(String templateName) {
        return new TemplateService().getTemplateName(templateName);
    }

    public static String replaceTemplate(String templateName, Map<String, String> mapping) {
        return new TemplateService().replaceTemplate(templateName, mapping);
    }
}
