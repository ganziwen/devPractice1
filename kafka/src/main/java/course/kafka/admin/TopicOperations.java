package course.kafka.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import javax.lang.model.SourceVersion;
import java.util.Collection;
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
    public void testCreateTopic() {
        /*
          topic 名字,分区数,副本数(必须小于等于broker数量)
         */
        NewTopic newTopic = new NewTopic(TOPIC_NAME, 1, Short.parseShort("1"));

        CreateTopicsResult topics = adminClient.createTopics(Collections.singletonList(newTopic));
        topics.values().forEach((name, feature) -> {
            System.out.println("name = " + name);
            System.out.println("feature = " + feature);
        });

    }

    /**
     * 查找
     */
    @Test
    public void testListTopics() {
        final ListTopicsResult listTopicsResult = adminClient.listTopics();
        try {
            final Set<String> names = listTopicsResult.names().get();
            names.forEach(name -> System.out.println("name = " + name));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除
     */
    @Test
    public void testRemoveTopics() throws ExecutionException, InterruptedException {
        DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singletonList(TOPIC_NAME));
        KafkaFuture<Void> voidKafkaFuture = deleteTopicsResult.all();
        for (Map.Entry<String, KafkaFuture<Void>> entry : deleteTopicsResult.values().entrySet()) {
            String name = entry.getKey();
            KafkaFuture<Void> future = entry.getValue();
        }
    }
}
