package com.example.autoframework.model;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import org.assertj.core.util.Lists;

import javax.crypto.interfaces.PBEKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName DataEntity
 * @Description DataEntity 代表的是
 * - name: Bob
 * id: 2
 * @date 2021/12/18 11:26
 */
@Data
public class DataEntity {
    private Map<String, Entity> entityMap;
    private List<Entity> entityList;

    public DataEntity() {
        this.entityMap = Maps.newHashMap();
        this.entityList = Lists.newArrayList();
    }

    public void addEntity(Entity entity) {
        this.entityMap.put(entity.key, entity);
        this.entityList.add(entity);
    }


    public Map<String, Entity> getEntityMap() {
        return this.entityMap;
    }

    public boolean isKeyExist(String key) {
        return this.entityMap.containsKey(key);
    }

    public Entity getEntity(String key) {
        return this.entityMap.get(key);
    }

    /**
     * name: Bob 是单的的 Entity
     */
    @Data
    public static class Entity {
        boolean isJavaBean;
        String key;
        String val;

        public Entity(boolean isJavaBean, String key, String val) {
            this.isJavaBean = isJavaBean;
            this.key = key;
            this.val = val;
        }

        public static Entity of(boolean isJavaBean, String key, String val) {
            return new Entity(isJavaBean, key, val);
        }
    }
}
