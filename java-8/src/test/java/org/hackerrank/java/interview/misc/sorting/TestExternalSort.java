package org.hackerrank.java.interview.misc.sorting;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;

public class TestExternalSort {

    @Test
    public void segmentationTest() throws Exception {
        int testArraySize = 1024;
        int memSize = 32;

        byte[] array = generateRandom(testArraySize);
        System.out.println(Arrays.toString(array));

        File fSource = File.createTempFile("source", ".tmp");
        FileUtils.writeByteArrayToFile(fSource, array);
        File fTarget = File.createTempFile("target", ".tmp");
        ExternalSort sort = new ExternalSortInPlace();
        sort.sort(fSource.getAbsolutePath(), fTarget.getAbsolutePath(), memSize);

        byte[] result = new byte[testArraySize];
        try (InputStream in = new BufferedInputStream(new FileInputStream(fTarget))) {
            IOUtils.readFully(in, result);
        }
        Assert.assertTrue(SortUtils.isArraySorted(result));
    }

    private byte[] generateRandom(int length) {
        Random random = new Random();
        byte[] array = new byte[length];
        random.nextBytes(array);
        return array;
    }


}
