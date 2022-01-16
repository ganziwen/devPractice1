package com.development.mock.observer;

import cn.hutool.core.thread.ThreadUtil;
import com.development.mock.model.MockContext;
import org.tinylog.Logger;

import java.util.Objects;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TimeOutResponseObserver
 * @Description
 * @date 2022/1/16 12:52
 */
public class TimeOutResponseObserver implements IObserver<MockContext> {


    @Override
    public void update(MockContext mockContext) {
        // 建设点：超时处理
        Logger.info("time out start ,timeout = {}ms", mockContext.getTimeOut());
        if (Objects.isNull(mockContext.getTimeOut()) || mockContext.getTimeOut() <= 0) {
            return;
        } else {
            Logger.info("time is sleeping……");
            ThreadUtil.sleep(mockContext.getTimeOut());
            Logger.info("time sleep end");

        }
    }
}
