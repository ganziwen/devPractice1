package com.example.autoframework.template.factory;

import com.example.autoframework.model.TemplateInfo;
import com.example.autoframework.template.service.TemplateService;
import org.junit.jupiter.api.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TemplateFactoryTest
 * @Description
 * @date 2021/11/27 20:24
 */
public class TemplateFactoryTest {
    @Test
    public void testTemp() {
        TemplateInfo defaultAlarmTemplate = TemplateFactory.of().getTemplateByName("default_alarm_template");
        TemplateInfo defaultReportTemplate = TemplateFactory.of().getTemplateByName("default_report_template");
        System.out.println("defaultAlarmTemplate = " + defaultAlarmTemplate);
        System.out.println("defaultReportTemplate = " + defaultReportTemplate);
    }

    @Test
    public void testEmptyTemp() {
        TemplateService templateService = new TemplateService();
        TemplateInfo templateName = templateService.getTemplateName("");
        System.out.println("templateName = " + templateName);
    }
}
