package com.example.demo.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOperations {

    public static List<Integer> difference(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>(list1);
        result.removeAll(list2);
        return result;
    }

    public static List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>(list1);
        result.retainAll(list2);
        return result;
    }

    public static List<Integer> union(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set = new HashSet<>(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }
}