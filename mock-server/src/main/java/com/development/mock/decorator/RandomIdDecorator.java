package com.development.mock.decorator;

import com.development.mock.util.RandomUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:52
 */
public class RandomIdDecorator extends BasePackageResponseDecorator {

    public static final Pattern PATTERN = Pattern.compile("\\$\\{random:id:(.*)}");

    public RandomIdDecorator(BasePackageResponseDecorator innerDecorator) {
        super(innerDecorator);
    }

    @Override
    protected String onDecorat(String data) {
        // if (data.contains("${random:id}")) {
        //     return data.replaceAll("\\$\\{random:id}", RandomUtils.random32Id());
        // }
        Matcher matcher = PATTERN.matcher(data);
        while (matcher.find()) {
            String element = matcher.group(0);
            int size = Integer.parseInt(element);
            String randomId = RandomUtils.randomId(size);
            data = StringUtils.replace(data, element, randomId);
        }
        return data;
    }


}
