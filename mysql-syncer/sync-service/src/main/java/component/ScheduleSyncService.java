package component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ScheduleSyncService
 * @Description
 * @date 2021/10/4 16:15
 */
@Component
public class ScheduleSyncService {
    @Scheduled(cron = "*/30 * * * * *")
    public void diff() {
        // 拿到差异
        // 周知，周知的一般是邮件，钉钉或者其他的方式，可以封装在 common 里面进行调用
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void sync() {
        // 拿到差异
        // 自动同步
    }
}
