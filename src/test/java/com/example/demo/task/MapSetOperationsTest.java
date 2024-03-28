package com.example.demo.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapSetOperationsTest {

    @Test
    @DisplayName("차집합 테스트")
    public void differenceTest() {
        // Given
        Map<Integer, Object> map1 = new HashMap<>();
        map1.put(1, null);
        map1.put(2, null);
        map1.put(3, null);
        map1.put(4, null);
        map1.put(5, null);

        Map<Integer, Object> map2 = new HashMap<>();
        map2.put(4, null);
        map2.put(5, null);
        map2.put(6, null);
        map2.put(7, null);
        map2.put(8, null);

        // When
        Map<Integer, Object> difference = MapSetOperations.difference(map1, map2);

        Map<Integer, Object> expectedDifference = new HashMap<>();
        expectedDifference.put(1, null);
        expectedDifference.put(2, null);
        expectedDifference.put(3, null);

        // Then
        assertEquals(expectedDifference, difference);
    }

    @Test
    @DisplayName("교집합 테스트")
    public void testIntersection() {
        // Given
        Map<Integer, Object> map1 = new HashMap<>();
        map1.put(1, null);
        map1.put(2, null);
        map1.put(3, null);
        map1.put(4, null);
        map1.put(5, null);

        Map<Integer, Object> map2 = new HashMap<>();
        map2.put(4, null);
        map2.put(5, null);
        map2.put(6, null);
        map2.put(7, null);
        map2.put(8, null);

        // When
        Map<Integer, Object> intersection = MapSetOperations.intersection(map1, map2);

        Map<Integer, Object> expectedIntersection = new HashMap<>();
        expectedIntersection.put(4, null);
        expectedIntersection.put(5, null);

        // Then
        assertEquals(expectedIntersection, intersection);
    }

    @Test
    @DisplayName("합집합 테스트")
    public void testUnion() {
        // Given
        Map<Integer, Object> map1 = new HashMap<>();
        map1.put(1, null);
        map1.put(2, null);
        map1.put(3, null);
        map1.put(4, null);
        map1.put(5, null);

        Map<Integer, Object> map2 = new HashMap<>();
        map2.put(4, null);
        map2.put(5, null);
        map2.put(6, null);
        map2.put(7, null);
        map2.put(8, null);

        Map<Integer, Object> union = MapSetOperations.union(map1, map2);

        Map<Integer, Object> expectedUnion = new HashMap<>();
        expectedUnion.putAll(map1);
        expectedUnion.putAll(map2);

        // When
        assertEquals(expectedUnion, union);
    }
}