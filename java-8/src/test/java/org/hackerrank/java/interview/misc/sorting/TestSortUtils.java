package org.hackerrank.java.interview.misc.sorting;

import org.junit.Assert;
import org.junit.Test;

import static org.hackerrank.java.interview.misc.sorting.SortUtils.isArraySorted;

public class TestSortUtils {

    @Test
    public void testArraySorted() throws Exception {
        Integer[] sortedArray = {0, 1, 1, 2};
        Assert.assertTrue(isArraySorted(sortedArray));

        Integer[] unsortedArray = {0, 1, 0, 2, 0, 1};
        Assert.assertFalse(isArraySorted(unsortedArray));

        Integer[] emptyArray = {};
        Assert.assertTrue(isArraySorted(emptyArray));

        Integer[] oneElementArray = {1};
        Assert.assertTrue(isArraySorted(oneElementArray));
    }
}
