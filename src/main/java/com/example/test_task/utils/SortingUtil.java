package com.example.test_task.utils;

import java.util.ArrayList;
import java.util.List;

public class SortingUtil {

    /**
     * Сортирует список чисел по убыванию с помощью алгоритма MergeSort
     */
    public static List<Integer> mergeSort(List<Integer> numbers) {

        if (numbers == null || numbers.size() <= 1) {
            return numbers;
        }

        int mid = numbers.size() / 2;

        List<Integer> left = mergeSort(new ArrayList<>(numbers.subList(0, mid)));
        List<Integer> right = mergeSort(new ArrayList<>(numbers.subList(mid, numbers.size())));

        return mergeDescending(left, right);
    }

    private static List<Integer> mergeDescending(List<Integer> left, List<Integer> right) {

        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) >= right.get(j)) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }
        return result;
    }
}