package org.hackerrank.java.interview.misc.sorting;

import java.io.IOException;

public interface ExternalSort {
    void sort(String source, String target, int memorySize) throws IOException;
}
