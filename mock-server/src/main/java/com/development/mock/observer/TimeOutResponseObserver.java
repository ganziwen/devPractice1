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

        // 这里多线程进来可能会有问题，全部 sleep 在这里
        // Logger.info("time out start ,timeout = {}ms", mockContext.getTimeOut());
        // if (!Objects.isNull(mockContext.getTimeOut()) && mockContext.getTimeOut() > 0) {
        //     Logger.info("time is sleeping……");
        //     ThreadUtil.sleep(mockContext.getTimeOut());
        //     Logger.info("time sleep end");
        // } else {
        //     Logger.info("time out is not set or timeout value is 0");
        // }

        // 这里的操作可以解决第一个场景的问题
        long startTime = System.currentTimeMillis();
        while (true) {
            long currentTimeMillisTime = System.currentTimeMillis();
            if (currentTimeMillisTime - startTime >= mockContext.getTimeOut()) {
                break;
            }
            Thread.yield();
        }

        /*
        思考：
        1. 这样粗暴的 sleep 有没有问题？假设现在机器的 cpu 是 8 核，但是有 N(N>8) 多请求是在做超时处理
        2. 超时处理真的就这么简单吗？答案肯定是不行的
        场景1：比如做压测时的 mock 服务，多个线程请求进来难道全部在这 sleep 么？
        场景2：线上真实的响应时间返回，是不确定的：可能 20% 的流量，超时时间是 400-500 ms 之间随机；30% 的流量，超时时间是 500-1200 随机；另外的 50% 是 1200-1800 之间随机
         */
    }
}
