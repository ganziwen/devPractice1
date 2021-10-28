package course.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ConsumerOperations
 * @Description
 * @date 2021/10/26 16:34
 */
public class ConsumerOperations {
    private static final String TOPIC_NAME = "mykafka";
    private static final String HOST = "www.xiaowenshu.cn:9092";
    private KafkaConsumer<String, String> kafkaConsumer;
    private Properties properties;

    @BeforeEach
    public void genConsumer() {
        // consumer 的配置信息封装
        this.properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        // 创建 consumer
        this.kafkaConsumer = new KafkaConsumer<>(properties);
        // 指定监听的 topic
        this.kafkaConsumer.subscribe(Collections.singletonList(TOPIC_NAME));

    }

    @AfterEach
    public void closeConsumer() {
        this.kafkaConsumer.close();

    }

    /**
     * 消费消息做自动提交(业务上比较少用)
     */
    @Test
    public void testConsumerForAutoCommit() {
        // 自动提交
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // 轮询处理消息
        while (true) {
            // 拉取消息
            ConsumerRecords<String, String> records = this.kafkaConsumer.poll(Duration.ofMillis(100));
            Iterator<ConsumerRecord<String, String>> iterator = records.records(TOPIC_NAME).iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> record = iterator.next();
                System.out.println("record.toString() = " + record.toString());
            }

        }
    }

    /**
     * 手动提交,实际上用的比较多.
     */
    @Test
    public void testConsumerForCommit() {
        this.properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // 轮询处理消息
        while (true) {
            // 拉取消息
            ConsumerRecords<String, String> records = this.kafkaConsumer.poll(Duration.ofMillis(100));
            Iterator<ConsumerRecord<String, String>> iterator = records.records(TOPIC_NAME).iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> record = iterator.next();
                System.out.println("record.toString() = " + record.toString());
            }
            /*
             * 手动提交,这里表示的是告诉 broker ,这个消息已经正确处理
             * 如果业务处理有问题,需要这条消息被继续处理,即被其他 consumer 处理
             * 那么此时就不需要调用下面这段代码,即未做提交,broker 未收到 ack ,就会认为没有被处理,也就是 offset 也不会往后移动,所以这条消息还可以被继续处理和消费
             * 这后面一般是跟着实际的业务场景:收到了消息,需要处理的内容封装完成之后,存入 Mysql
             */
            kafkaConsumer.commitAsync();

        }
    }
}
