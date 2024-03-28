package com.example.demo.task;

import java.util.HashMap;
import java.util.Map;

public class MapSetOperations {
    public static Map<Integer, Object> difference(Map<Integer, Object> map1, Map<Integer, Object> map2) {
        Map<Integer, Object> result = new HashMap<>(map1);
        for (Integer key : map2.keySet()) {
            result.remove(key);
        }
        return result;
    }

    public static Map<Integer, Object> intersection(Map<Integer, Object> map1, Map<Integer, Object> map2) {
        Map<Integer, Object> result = new HashMap<>(map1);
        result.keySet().retainAll(map2.keySet());
        return result;
    }

    public static Map<Integer, Object> union(Map<Integer, Object> map1, Map<Integer, Object> map2) {
        Map<Integer, Object> result = new HashMap<>(map1);
        result.putAll(map2);
        return result;
    }
}
