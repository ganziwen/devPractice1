package com.example.autoframework.extention.format;

import org.assertj.core.util.Lists;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName FormateManager
 * @Description
 * @date 2021/11/13 11:13
 */
public enum FormatManager {
    // 枚举实现单例
    INSTANCE;
    private final List<FormatObserver> observers;

    FormatManager() {
        // 注册所有消息变更的观察者
        this.observers = Lists.newArrayList(
                new CaseTagFormatObserver(),
                new CheckPointFormatObserver(),
                new DingTalkAlarmFormatObserver(),
                new CaseDescFormatObserver(),
                new CaseTitleFormatObserver(),
                new CaseGroupFormatObserver()
        );
    }

    // 回调消息到达，触发所有观察者来处理消息
    public void doFormatcheck(Method method) {
        for (FormatObserver observer : observers) {
            observer.format(method);
        }
    }
}
