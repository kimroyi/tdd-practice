package com.example.demo.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SetOperationsTest {
    @Test
    @DisplayName("차집합 테스트")
    void differenceTest() {
        // Given
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        // When
        List<Integer> difference = SetOperations.difference(list1, list2);

        // Then
        List<Integer> expectedDifference = Arrays.asList(1, 2, 3);
        assertThat(3).isEqualTo(difference.size());
        assertThat(difference).isEqualTo(expectedDifference);
    }

    @Test
    @DisplayName("교집합 테스트")
    public void intersectionTest() {
        // Given
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        // When
        List<Integer> intersection = SetOperations.intersection(list1, list2);

        // Then
        List<Integer> expectedIntersection = Arrays.asList(4, 5);
        assertThat(intersection).isEqualTo(expectedIntersection);
    }

    @Test
    @DisplayName("합집합 테스트")
    public void unionTest() {
        // Given
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        // When
        List<Integer> union = SetOperations.union(list1, list2);

        // Then
        List<Integer> expectedUnion = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(union).isEqualTo(expectedUnion);
    }
}