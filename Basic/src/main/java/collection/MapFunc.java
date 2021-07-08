package collection;

import java.util.*;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/7/8-22:01
 */
public class MapFunc {
    public static void main(String[] args) {
        testMap();
    }

    public static void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "cc");
        System.out.println("map = " + map);
        map.put("bb", "bb");
        System.out.println("map = " + map);

        System.out.println("map.containsKey(\"aa\") = " + map.containsKey("aa"));


        System.out.println("map.get(\"aa\") = " + map.get("aa"));

//        取不到值返回一个默认值
        System.out.println("map.getOrDefault(\"ff\",\"88\") = " + map.getOrDefault("ff", "88"));

        Set<String> set = map.keySet();
        System.out.println("set = " + set);

        Collection<String> collection = map.values();
        System.out.println("collection = " + collection);

//        Collection 转 List
        List<String> list = new ArrayList<>(map.values());
        System.out.println("list = " + list);

//        拆解成单个键值对,用来遍历 map
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
//        利用 forEach 遍历
        map.forEach((k, v) -> System.out.println("key:" + k + ",val:" + v));
    }
}
