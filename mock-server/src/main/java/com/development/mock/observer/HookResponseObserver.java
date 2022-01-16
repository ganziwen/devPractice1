package com.development.mock.observer;

import com.development.mock.decorator.DecoratorManager;
import com.development.mock.model.HookContext;
import com.development.mock.model.MockContext;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import org.tinylog.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName HookResponseObserver
 * @Description
 * @date 2022/1/16 10:08
 */
public class HookResponseObserver implements IObserver<MockContext> {

    public static final Pattern PATTERN = Pattern.compile("(?<=\\$\\{hook:)(.*?)(?=\\})");

    @Override
    public void update(MockContext mockContext) {
        Logger.info("start hook");
        HookContext hookContext = HookContext.builder().finalResponse(mockContext.getFinalResponse()).requestParams(mockContext.getRequestParams()).build();
        DecoratorManager.newInstance.doHook(hookContext);
        mockContext.setFinalResponse(hookContext.getFinalResponse());
    }
}
