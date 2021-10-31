package com.example.demo.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.Optional;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName CommonConsumer
 * @Description
 * @date 2021/10/31 16:46
 */
public class CommonConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CommonConsumer.class);


    @KafkaListener(topics = KafkaConst.TOPIC_NAME, groupId = KafkaConst.GROUP_ONE)
    public void consumeGroupOne(ConsumerRecord<String, String> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<String> msgOptional = Optional.ofNullable(record.value());
        if (msgOptional.isPresent()) {
            String msg = msgOptional.get();
            logger.info("group one start : topic = {} ,msg = {}", topic, msg);
            ack.acknowledge();
        }
    }

    @KafkaListener(topics = KafkaConst.TOPIC_NAME, groupId = KafkaConst.GROUP_TWO)
    public void consumeGroupTwo(ConsumerRecord<String, String> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional<String> msgOptional = Optional.ofNullable(record.value());
        if (msgOptional.isPresent()) {
            String msg = msgOptional.get();
            logger.info("group two start : topic = {} ,msg = {}", topic, msg);
            ack.acknowledge();
        }
    }
}
