package course.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ProducerOperations
 * @Description Producer 相关操作
 * @date 2021/10/24 17:00
 */
public class ProducerOperations {
    private static final String TOPIC_NAME = "hello-mykafka";
    private KafkaProducer<String, String> producer;

    @Before
    public void generateProducer() {
        // 构建 kafka producer 的配置参数
        Properties properties = new Properties();
        // 定义 server 的地址以及端口
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "www.xiaowenshu.cn:9092");
        // 定义序列化器,kafka 提供了很多,String 的,Integer 的,Byte,Long 的都有.要用自己的序列化器的话,可以自己实现自 Serializer 接口即可
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 构建出 kafka producer
        this.producer = new KafkaProducer<>(properties);
    }

    /**
     * 最后都需要关闭掉 Producer
     */
    @After
    public void closeProducer() {
        producer.close();
    }

    /**
     * 测试发送单条消息
     */
    @Test
    public void testSendMsg() {

        // 创建一个消息实体,指定 topic ,以及消息的 key 和 message
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, "input_key", "input_msg");

        // 消息发送
        producer.send(record);
    }

    /**
     * 测试发送多条消息(异步)
     */
    @Test
    public void testSendMultiMsg() {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, String.format("input_key_%s", i), String.format("input_msg_%s", i));
            producer.send(record);
        }
    }

    /**
     * 伪同步发送多条消息.所有的结果包装成 Future ,get 就是阻塞方法,也就是拿到上一个请求的返回才会发送下一个消息,所以这个会稍显慢一丢丢.用异步加阻塞完成的"同步"操作
     */
    @Test
    public void testSendMultiMsgWithSync() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, String.format("input_key_%s", i), String.format("input_msg_%s", i));
            // 处理结果返回,得到 future,future.get() 就是个阻塞操作,来模拟实现同步能力
            producer.send(record).get();
        }
    }

    /**
     * 消息发送+结果回调
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testSendMsgWithCallBack() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, String.format("input_key_%s", i), String.format("input_msg_%s", i));
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(String.format("send msg topic = %s,partition = %s,offset = %s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset()));
                }
            });
        }
    }


}
