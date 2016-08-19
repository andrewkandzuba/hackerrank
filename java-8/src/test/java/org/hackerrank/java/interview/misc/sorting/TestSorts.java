package org.hackerrank.java.interview.misc.sorting;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hackerrank.java.interview.misc.sorting.SortUtils.isArraySorted;

public class TestSorts {
    @Test
    public void testMeasureSorts() throws Exception {
        List<Sort<Integer>> sorts = Arrays.asList(new HeapSort<>(), new MergeSort<>(), new InsertSort<>(), new QuickSort<>());
        sorts.forEach(sort -> SortUtils.measure(sort, 10000, 10));
    }

    @Test
    public void testSortRaceCondition() throws Exception {
        List<Sort<Integer>> sorts = Arrays.asList(new HeapSort<>(), new MergeSort<>(), new QuickSort<>());
        SortUtils.sortRaceCondition(sorts, 10000);
    }

    @Test
    public void testQuickSort() throws Exception {
        Integer[] array = new Integer[]{9, 4, -1, 8, 9, 4, 0, 15, 2, -3};
        Sort<Integer> sort = new QuickSort<>();
        array = sort.sort(array);
        Assert.assertTrue(isArraySorted(array));

        Integer[] anotherArray = new Integer[]{0, 0, 12, -8, 17, -4, 21, 15, 2, -3, 34, -13, -13, 0};
        anotherArray = sort.sort(anotherArray);
        Assert.assertTrue(isArraySorted(anotherArray));
    }
}
