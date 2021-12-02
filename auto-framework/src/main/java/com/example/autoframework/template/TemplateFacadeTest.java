package com.example.autoframework.template;

import com.example.autoframework.model.TemplateInfo;
import com.example.autoframework.template.service.TemplateService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TemplateFactoryTest
 * @Description
 * @date 2021/11/27 20:24
 */
public class TemplateFacadeTest {
    @Test
    public void testTemp() {
        TemplateInfo defaultAlarmTemplate = TemplateFacade.getTemplateByName("default_alarm_template");
        TemplateInfo defaultReportTemplate = TemplateFacade.getTemplateByName("default_report_template");

        System.out.println("defaultAlarmTemplate = " + defaultAlarmTemplate);
        System.out.println("defaultReportTemplate = " + defaultReportTemplate);
    }

    @Test
    public void testReplace() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "my_title");
        map.put("id", "my_id");
        map.put("info", "my_info");
        String defaultAlarmTemplate = TemplateFacade.replaceTemplate("default_alarm_template", map);
        System.out.println("defaultAlarmTemplate = " + defaultAlarmTemplate);
    }

    @Test
    public void testEmptyTemp() {
        TemplateService templateService = new TemplateService();
        TemplateInfo templateName = templateService.getTemplateName("");
        System.out.println("templateName = " + templateName);
    }
}
