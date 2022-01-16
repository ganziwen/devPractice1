package com.development.mock.decorator;

import com.development.mock.util.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2022/1/10-21:52
 */
public class RandomStrDecorator extends BaseResponseDecorator<String> {
    public static final Pattern PATTERN = Pattern.compile("(?<=\\$\\{random:str:)(.*?)(?=\\})");

    public RandomStrDecorator(BaseResponseDecorator innerDecorator) {
        super(innerDecorator);
    }

    @Override
    protected String onDecorate(String data) {
        // if (data.contains("${random:id}")) {
        //     return data.replaceAll("\\$\\{random:id}", RandomUtils.random32Id());
        // }
        Matcher matcher = PATTERN.matcher(data);
        while (matcher.find()) {
            // String element = matcher.group(0);
            int size = Integer.parseInt(matcher.group(0));
            String searchElement = genSearchElement(size);
            String randomString = RandomUtils.randomStr(size);
            data = StringUtils.replace(data, searchElement, randomString);
        }
        return data;
    }

    private String genSearchElement(int size) {
        return String.format("${random:str:%d}", size);
    }

}
