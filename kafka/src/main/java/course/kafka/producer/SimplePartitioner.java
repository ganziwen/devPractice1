package course.kafka.producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SimplePartitionar
 * @Description
 * @date 2021/10/26 14:40
 */
public class SimplePartitioner implements Partitioner {

    /**
     * 假设 passport 需要有 0-3 这几个分区,hotel 只需要一个 4 分区.只是简单演示,真实的逻辑肯定比这个复杂的多
     *
     * @param topic
     * @param key
     * @param bytes
     * @param value
     * @param valueBytes
     * @param cluster
     * @return
     */
    @Override
    public int partition(String topic, Object key, byte[] bytes, Object value, byte[] valueBytes, Cluster cluster) {
        if (StringUtils.startsWith(key.toString(), "passPort")) {
            // 确保 hash code 为正整数
            int code = Math.abs(key.toString().hashCode());
            return code % 3;
        }
        return 4;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
