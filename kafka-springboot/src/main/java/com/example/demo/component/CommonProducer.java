package com.example.demo.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ComponentProducer
 * @Description
 * @date 2021/10/31 16:22
 */
@Component
public class CommonProducer {
    private static final Logger logger = LoggerFactory.getLogger(CommonProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KafkaConst.TOPIC_NAME, msg);
        // 回调,不需要的话就不写这块也是 ok 的
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("producer send failed. msg = {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                logger.info("producer send successful. msg = {}", stringStringSendResult.toString());
            }
        });
    }
}
