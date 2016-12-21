package org.hackerrank.java.interview.misc.sorting;


import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.Random;

public class TestExternalSort {

    @Test
    public void testSegmentation() throws Exception {
        byte[] array = generateRandom(1024);
        File fSource = File.createTempFile("source", ".tmp");
        FileUtils.writeByteArrayToFile(fSource, array);
        File fTarget = File.createTempFile("target", ".tmp");
        ExternalSort sort = new ExternalSort(32);
        sort.sort(fSource.getAbsolutePath(), fTarget.getAbsolutePath());
    }

    private byte[] generateRandom(int length){
        Random random = new Random(1024);
        byte[] array = new byte[length];
        random.nextBytes(array);
        return array;
    }
}
