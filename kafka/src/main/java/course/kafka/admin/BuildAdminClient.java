package course.kafka.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName BuildAdminClient
 * @Description 创建 AdminClient ,创建时指定相应配置,目前最简化出 kafka broker 地址即可,bootstrap.servers=www.xiaowenshu.cn:9092
 * @date 2021/10/23 11:48
 */
public class BuildAdminClient {
    public static AdminClient createAdminClient() {
        Properties properties = new Properties();
        // 配置 kafka 的 broker 地址
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "www.xiaowenshu.cn:9092");
        return AdminClient.create(properties);
    }


    @Test
    public void testBuildAdminClient() {
        AdminClient adminClient = BuildAdminClient.createAdminClient();
        assertThat(adminClient).isNotNull();
    }
}
