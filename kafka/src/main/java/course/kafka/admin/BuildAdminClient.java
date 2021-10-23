package course.kafka.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;

import java.util.Properties;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName BuildAdminClient
 * @Description
 * @date 2021/10/23 11:48
 */
public class BuildAdminClient {
    public static AdminClient createAdminClient() {
        Properties properties = new Properties();
        // 配置 kafka 的 broker 地址
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "www.xiaowenshu.cn");
        AdminClient adminClient = AdminClient.create(properties);
        return adminClient;
    }
}
