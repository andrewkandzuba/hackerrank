package org.hackerrank.java.interview.misc.sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestSorts {
    @Test
    public void testMeasureSorts() throws Exception {
        List<Sort<Integer>> sorts = Arrays.asList(new HeapSort<>(), new MergeSort<>(), new InsertSort<>());
        sorts.stream().forEach(sort -> SortUtils.measure(sort, 10000, 10));
    }

    @Test
    public void testSortRaceCondition() throws Exception {
        List<Sort<Integer>> sorts = Arrays.asList(new HeapSort<>(), new MergeSort<>());
        SortUtils.sortRaceCondition(sorts, 10000);
    }
}
