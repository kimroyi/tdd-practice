package com.example.demo.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class MapSortTest {

    @Test
    void dtoSortTest() {
        List<Product> list = getProducts();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> mapList = objectMapper.convertValue(list, objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

        Comparator<Map<String, Object>> comparing = Comparator.comparing(map -> {
            String orderDate = (String) map.get("orderDate");
            String orderFailDate = (String) map.get("orderFailDate");
            return orderDate == null ? orderFailDate : orderDate ;
        });

        String sortBy = "ASC";
        Comparator<Map<String, Object>> comparator = "ASC".equalsIgnoreCase(sortBy) ? comparing : comparing.reversed();

        List<Map<String, Object>> products = mapList.stream()
                .sorted(comparator)
                .toList();

        products.forEach(System.out::println);
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product(1, "키보드", 100000, "2024-08-28 20:59:00", null, "A"));
        products.add(new Product(2, "닌텐도", 77000, null, "2024-08-28 20:19:00", "A"));
        products.add(new Product(3, "키스킨", 10000, null, "2024-08-28 20:19:10", "A"));
        products.add(new Product(1, "모니터", 400000, null, "2024-08-28 20:49:00", "B"));
        products.add(new Product(2, "노트북", 110000, null, "2024-08-28 20:29:00", "B"));
        products.add(new Product(1, "마우스", 30000, "2024-08-28 20:39:00", null, "C"));

        return products;
    }

    @Data
    @AllArgsConstructor
    public static class Product {

        private int seq;
        private String productName;
        private int price;
        private String orderDate;
        private String orderFailDate;
        private String type;
    }
}
