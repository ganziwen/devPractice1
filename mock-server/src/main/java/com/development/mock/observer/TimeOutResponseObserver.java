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
        /*
        建设点：超时处理,模拟服务超时场景
        实现思路：
        1. 既然要实现超时能力，即在请求到达时让线程 sleep 来模拟程序内部的超时场景
        2. 在 mock 匹配时，多加一个字段：timeout: xxx 毫秒，来定义
        3. 如果 MockDataInfo 中有此字段的配置，即需要实现超时的能力
        4. 如果没有配置则逻辑正常往下处理
         */
        Logger.info("time out start ,timeout = {}ms", mockContext.getTimeOut());
        if (!Objects.isNull(mockContext.getTimeOut()) && mockContext.getTimeOut() > 0) {
            Logger.info("time is sleeping……");
            ThreadUtil.sleep(mockContext.getTimeOut());
            Logger.info("time sleep end");
        } else {
            Logger.info("time out is not set or timeout value is 0");
        }

        /*
        思考：
        1. 这样粗暴的 sleep 有没有问题？假设现在机器的 cpu 是 8 核，但是有 N(N>8) 多请求是在做超时处理
        2. 超时处理真的就这么简单吗？
        场景：比如做压测时的 mock 服务
         */
    }
}
