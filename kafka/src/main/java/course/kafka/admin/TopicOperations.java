package course.kafka.admin;

import com.google.common.collect.Maps;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.config.ConfigResource;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.lang.model.SourceVersion;
import javax.sound.midi.VoiceStatus;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName TopicOperations
 * @Description Topic 操作:创建、删除、配置、描述
 * @date 2021/10/23 16:15
 */
public class TopicOperations {

    private static final String TOPIC_NAME = "hello-mykafka";
    private AdminClient adminClient;

    @Before
    public void genNewClient() {
        AdminClient adminClient = BuildAdminClient.createAdminClient();
    }

    @After
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
        // 为啥提供的 api 是可以删除多个,而不是一个一个删除,并且没有提供一个一个删除的接口
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singletonList(TOPIC_NAME));

        // 第一种
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
    public void testUpdateTopicPartition() throws ExecutionException, InterruptedException {

        // 构建修改 Topic 的请求
        Map<String, NewPartitions> newPartitions = Maps.newHashMap();
        // inCreaseTo 代表需要增加的分区数量
        newPartitions.put(TOPIC_NAME, NewPartitions.increaseTo(1));

        CreatePartitionsResult partitions = adminClient.createPartitions(newPartitions);
        partitions.all().get();
    }
}
