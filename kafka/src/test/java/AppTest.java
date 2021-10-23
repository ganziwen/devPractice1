import course.kafka.admin.BuildAdminClient;
import org.apache.kafka.clients.admin.AdminClient;
import org.junit.Test;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName AppTest
 * @Description
 * @date 2021/10/23 11:55
 */
public class AppTest {
    @Test
    public void testCreateAdmin() {
        AdminClient adminClient = BuildAdminClient.createAdminClient();
        adminClient.listTopics();
    }
}
