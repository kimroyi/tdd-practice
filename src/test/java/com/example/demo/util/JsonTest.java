package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonTest {

    @Test
    void map2Json() throws JsonProcessingException {

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map1 = Map.of("id", "a1", "name", "이름1");
        Map<String, String> map2 = Map.of("id", "a2", "name", "이름2");
        list.add(map1);
        list.add(map2);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        System.out.println("list = " + list);
        System.out.println("map = " + map1);
        System.out.println("json = " + json);
    }

    @Test
    void json2Map() throws JsonProcessingException {
        String json = "[{\"id\":\"a1\",\"name\":\"이름1\"},{\"id\":\"a2\",\"name\":\"이름2\"}]";

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Map<String, Object>>> typeReference = new TypeReference<>() {};

        List<Map<String, Object>> list = objectMapper.readValue(json, typeReference);
        System.out.println("json2Map = " + list);
    }
}
