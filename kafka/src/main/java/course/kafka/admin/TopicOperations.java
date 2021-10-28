package course.kafka.admin;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.ConfigResource.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TopicOperations
 * @Description Topic 操作:创建、删除、配置、描述
 * @date 2021/10/23 16:15
 */
public class TopicOperations {

    private static final String TOPIC_NAME = "myKafkaTest";
    private AdminClient adminClient;

    @BeforeEach
    public void genNewClient() {
        this.adminClient = BuildAdminClient.createAdminClient();
    }

    @AfterEach
    public void closeClient() {
        // 资源关闭
        adminClient.close();
    }

    /**
     * 创建
     */
    @Test
    public void testCreateTopic() throws ExecutionException, InterruptedException {
        /*
          topic 名字,分区数,副本数(必须小于等于broker数量)
         */
        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, Short.parseShort("1"));

        CreateTopicsResult topics = adminClient.createTopics(Collections.singletonList(newTopic));

        // 这里的 get 有啥用呢 ?
        topics.all().get();

        topics.values().forEach((name, feature) -> {
            System.out.println("name = " + name);
            System.out.println("feature = " + feature);
        });

    }

    /**
     * 查找
     */
    @Test
    public void testListTopics() throws ExecutionException, InterruptedException {
        final ListTopicsResult listTopicsResult = adminClient.listTopics();

        final Set<String> names = listTopicsResult.names().get();
        names.forEach(name -> System.out.println("name = " + name));


    }

    /**
     * 删除
     */
    @Test
    public void testRemoveTopics() throws ExecutionException, InterruptedException {
        /*
         * 为啥提供的 api 是可以删除多个,而不是一个一个删除,并且没有提供一个一个删除的接口?
         * 是因为它增删改查特别快,限制 kafka 的是带宽而不是处理量,删除单个 Topic 相当于你去北京买一瓶矿泉水回来,可以但是没有必要
         * 也就是你得传进来一个列表进行批量操作,原来需要调 100 次现在只需要调用一次即可
         */

        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singletonList(TOPIC_NAME));

        /*
         * 第一种,返回的都是 KafkaFeature ,再去 get ,这是干啥用的
         * get 是个阻塞方法,假设我们不去调用 get 方法,那么完了就是完了,什么时候拿到结果我也不关心也不管
         * 但是 get 在这的话是一定要拿到结果的,没拿到结果是不会往下执行
         */
        deleteTopicsResult.all().get();

        // 第二种
        KafkaFuture<Void> voidKafkaFuture = deleteTopicsResult.all();
        for (Map.Entry<String, KafkaFuture<Void>> entry : deleteTopicsResult.values().entrySet()) {
            String name = entry.getKey();
            KafkaFuture<Void> future = entry.getValue();
        }
    }

    /**
     * 获取指定 topic 的描述信息；https://kafka.apachecn.org/documentation.html#configuration
     */
    @Test
    public void testDescribeTopic() throws ExecutionException, InterruptedException {
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singletonList(TOPIC_NAME));
        Map<String, TopicDescription> topicDescriptionMap = describeTopicsResult.all().get();
        // desc 内有 partition 信息等
        topicDescriptionMap.forEach((name, desc) -> {
            System.out.println("name = " + name);
            System.out.println("desc = " + desc);
        });
    }

    /**
     * 获取 Topic 的配置描述信息
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testDescribeConfig() throws ExecutionException, InterruptedException {
        final ConfigResource configResource = new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);
        final DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Collections.singletonList(configResource));
        final Map<ConfigResource, Config> configResourceConfigMap = describeConfigsResult.all().get();
        configResourceConfigMap.forEach((name, config) -> {
            System.out.println("name = " + name);
            System.out.println("config = " + config);
        });
    }

    /**
     * 修改 topic 的分区数量,注意只能加不能减少
     */
    @Test
    public void testUpdateTopicPartition() throws ExecutionException, InterruptedException {

        // 构建修改 Topic 的请求
        Map<String, NewPartitions> newPartitions = Maps.newHashMap();
        // inCreaseTo 代表需要增加的分区数量
        newPartitions.put(TOPIC_NAME, NewPartitions.increaseTo(1));

        CreatePartitionsResult partitions = adminClient.createPartitions(newPartitions);
        partitions.all().get();
    }

    /**
     * 修改 Topic 的配置
     */
    @Test
    public void testUpdateTopicConfig() {
        Map<ConfigResource, Config> configMap = Maps.newHashMap();
        ConfigResource configResource = new ConfigResource(Type.TOPIC, TOPIC_NAME);
        final Config config = new Config(Collections.singletonList(new ConfigEntry("preallocate", "true")));
        configMap.put(configResource, config);
        // 修改 Topic 配置,用的老 api ,过时但是好用
        adminClient.alterConfigs(configMap);

        // 新 api ,但是不是很好用
        // adminClient.incrementalAlterConfigs();
    }
}
