package org.hackerrank.java.twitter;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestWaterPool {

    @Test
    public void puddlesVolume() throws Exception {
        Assert.assertEquals(10, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(2, 5, 1, 2, 3, 4, 7, 7, 6))));
        Assert.assertEquals(6, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(2, 5, 2, 5, 2, 7))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(2, 2, 2, 2, 2, 2))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(5, 2, 2, 2, 0, 0))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(0, 0, 2, 2, 2, 5))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(0, 2, 2, 5, 2, 0))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(0, 2))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(2, 0))));
        Assert.assertEquals(0, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(0, 0))));
        Assert.assertEquals(14, totalPaddlesVolume(Collections.unmodifiableList(Arrays.asList(2, 5, 1, 2, 7, 3, 4, 7, 7, 6))));
    }

    private static int totalPaddlesVolume(List<Integer> heights) throws Exception {
        if (heights == null) {
            throw new Exception("Null as a height parameter's value is not allowed!!!");
        }
        if (heights.size() <= 1) {
            return 0;
        }
        int totalPuddlesVolume = 0;
        int firstIndex = findHeightDepressionAfter(0, heights);
        int lastIndex = findHeightDepressionBefore(heights.size() - 1, heights);
        if (firstIndex == lastIndex) {
            return totalPuddlesVolume;
        }
        int minHeight = Math.min(heights.get(firstIndex), heights.get(lastIndex));
        for (int i = firstIndex; i < lastIndex; i++) {
            int currentHeight = heights.get(i);
            if (currentHeight > minHeight) {
                minHeight = Math.min(currentHeight, heights.get(lastIndex));
            }
            totalPuddlesVolume += Math.max(0, minHeight - currentHeight);
        }
        return totalPuddlesVolume;
    }

    private static int findHeightDepressionAfter(int index, List<Integer> height) {
        for (int i = index; i < height.size() - 1; i++) {
            if (height.get(i) > height.get(i + 1)) {
                return i;
            }
        }
        return index;
    }


    private static int findHeightDepressionBefore(int index, List<Integer> height) {
        for (int i = index; i > 0; i--) {
            if (height.get(i - 1) < height.get(i)) {
                return i;
            }
        }
        return index;
    }
}
