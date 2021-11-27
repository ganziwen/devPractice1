package com.example.autoframework.template.factory;

import cn.hutool.core.io.IoUtil;
import com.example.autoframework.model.TemplateInfo;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import sun.misc.IOUtils;

import javax.swing.plaf.basic.BasicTreeUI;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TemplateFactory
 * @Description 文件工厂类，将 resource 下的 template 模板文件 load 进内存里面，并且用文件名做key，文件内容作为 template_date 做映射。
 * 其他的程序来调用 factory 的时候，不关心你的模板文件放在哪，只需要传对应的数据，替换掉就完事了
 * @date 2021/11/27 18:51
 */
public class TemplateFactory {
    private static final String TEMPLATE_ROOT_PATH = "template";
    // 直接加载，目前模板较少，模板多的话还是要改成懒加载
    private final Map<String, TemplateInfo> templateMapping;

    private TemplateFactory() {
        this.templateMapping = initTemplateMapping();
    }

    /**
     * 将 template 下的文件捞出来，全部封装进 map 内，懒加载可以下次优化
     *
     * @return
     */
    private Map<String, TemplateInfo> initTemplateMapping() {
        // 拿的是路径下的文件
        Path rootPath = Paths.get(Resources.getResource(TEMPLATE_ROOT_PATH).getPath().substring(1));
        try {
            return Files.walk(rootPath)
                    .filter(Files::isRegularFile)
                    .map(path -> path.getFileName().toString())
                    .map(fileName -> TemplateInfo.builder()
                            .templateKey(fileName)
                            .templateValue(getTemplateData(fileName))
                            .build())
                    .collect(Collectors.toMap(TemplateInfo::getTemplateKey, temp -> temp));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private String getTemplateData(String fileName) {
        try {
            InputStream inputStream = Resources.getResource(TEMPLATE_ROOT_PATH + "/" + fileName).openStream();
            return IoUtil.read(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public TemplateInfo getTemplateByName(String templateName) {
        if (this.templateMapping.containsKey(templateName)) {
            return this.templateMapping.get(templateName);
        } else {
            throw new IllegalArgumentException("templateName isn't exist");
        }
    }


    private static class ClassHolder {
        private static final TemplateFactory HOLDER = new TemplateFactory();
    }

    public static TemplateFactory of() {
        return ClassHolder.HOLDER;
    }

}
