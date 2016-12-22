package org.hackerrank.java.interview.misc.sorting;

import java.io.IOException;

public class ExternalSortChunk extends ExternalSort {

    @Override
    public void sort(String source, String target, int memorySize) throws IOException {
        if(memorySize < 2) throw new IllegalArgumentException("Memory buffer size should be at least 2 bytes long");
    }
}
