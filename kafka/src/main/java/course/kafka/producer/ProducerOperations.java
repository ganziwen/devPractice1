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
    private Properties properties;

    @Before
    public void generateProducer() {
        // 构建 kafka producer 的配置参数
        this.properties = new Properties();
        // 定义 server 的地址以及端口
        this.properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "www.xiaowenshu.cn:9092");
        /*
         * ack 参数是为了保证发送请求的可靠性
         * acks = all 的情况下,意味着 leader 节点会等待所有同步中的副本确认之后再确认这条记录是否发送完成.只要至少有一个同步副本存在,记录就不会丢失.
         * 这种方式是对请求传递的最有效保证.ack = -1 和 ack = all 的 效果一致,all 和 -1 是最可靠的,必须收到消息,必须为 1 条
         * ack = 0,producer 不会等待服务器的反馈,最不可靠的,最多 1 条
         * ack = 1,节点会将记录写入本地日志,相对严格,消息会被收到 1 到多条,也就是至少 1 条
         */
        this.properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数
        this.properties.setProperty(ProducerConfig.RETRIES_CONFIG, "0");
        this.properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "16384");
        // 增加时间间隔,为的是防止夯死了客户端
        this.properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "16384");
        this.properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        // 定义序列化器,kafka 提供了很多,String 的,Integer 的,Byte,Long 的都有.要用自己的序列化器的话,可以自己实现自 Serializer 接口即可
        this.properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

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
     * "同步"发送多条消息(伪同步).所有的结果包装成 Future ,get 就是阻塞方法,也就是拿到上一个请求的返回,才会发送下一个消息,所以这个会稍显慢一丢丢.用异步加阻塞完成的"同步"操作
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
     * 消息发送+结果回调,但是不会阻塞发送.新起了个线程进行 callback 回调结果.这就是异步 + callback 的方式
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
                    System.out.printf("send msg topic = %s,partition = %s,offset = %s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                }
            });
        }
    }


    /**
     * 自定义的分区数
     */
    @Test
    public void testSendMsgWithPartition() {
        // 指定自定义的消息分区数
        this.properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, SimplePartitioner.class.getName());
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, String.format("input_key_%s", i), String.format("input_msg_%s", i));
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.printf("send msg topic = %s,partition = %s,offset = %s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
                }
            });
        }
    }
}
