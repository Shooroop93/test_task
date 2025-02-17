package com.example.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.test_task.utils.SortingUtil;
import org.junit.jupiter.api.Test;

public class SortingUtilTest {

    @Test
    void test_1() {
        List<Integer> result = SortingUtil.mergeSort(null);
        assertNull(result);
    }

    @Test
    void test_2() {
        List<Integer> input = Collections.emptyList();
        List<Integer> result = SortingUtil.mergeSort(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void test_3() {
        List<Integer> input = Collections.singletonList(5);
        List<Integer> result = SortingUtil.mergeSort(input);
        assertEquals(Collections.singletonList(5), result);
    }

    @Test
    void test_4() {
        List<Integer> input = Arrays.asList(1, 3);
        List<Integer> expected = Arrays.asList(3, 1);
        List<Integer> result = SortingUtil.mergeSort(input);
        assertEquals(expected, result);
    }

    @Test
    void test_5() {
        List<Integer> input = Arrays.asList(7, 2, 9, 4, 3, 8, 5, 1, 6);
        List<Integer> expected = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> result = SortingUtil.mergeSort(input);
        assertEquals(expected, result);
    }

    @Test
    void test_6() {
        List<Integer> input = Arrays.asList(5, 5, 3, 8, 8, 2);
        List<Integer> expected = Arrays.asList(8, 8, 5, 5, 3, 2);
        List<Integer> result = SortingUtil.mergeSort(input);
        assertEquals(expected, result);
    }
}