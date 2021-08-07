package com.learn.springboot.crontab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName PrintDataTime
 * @Description
 * @date 2021/8/7 18:12
 */
@Component
public class PrintDataTime {
    private static final Logger logger = LoggerFactory.getLogger(PrintDataTime.class);

    /**
     * 5s 打印一次
     */
    @Scheduled(cron = "*/5 * * * * *")
    public void printTime() {
        // 以下就是任务调度的业务逻辑代码处理
        logger.info("schedule print time = {}", LocalDateTime.now());
    }
}
