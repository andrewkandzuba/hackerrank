package org.hackerrank.java.interview.misc.sorting;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class ExternalSortInPlace extends ExternalSort {

    public void sort(String source, String target, int memorySize) throws IOException {
        if (memorySize % 2 > 0)
            throw new IllegalArgumentException("Sorting memory block should be even");

        int bs = memorySize / 2;
        int chunks = 0;
        byte[] byteBuffer = new byte[memorySize];

        try (FileChannel sourceRaf = FileChannel.open(Paths.get(source), StandardOpenOption.READ);
             FileChannel targetRaf = FileChannel.open(Paths.get(target), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, StandardOpenOption.READ)) {

            int read = sourceRaf.read(ByteBuffer.wrap(byteBuffer));
            while (read != -1) {
                chunks++;
                innerSort.sort(byteBuffer, read);
                targetRaf.write(ByteBuffer.wrap(byteBuffer, 0, read));
                read = sourceRaf.read(ByteBuffer.wrap(byteBuffer));
            }
        }

        try (FileChannel targetRaf = FileChannel.open(Paths.get(target), StandardOpenOption.WRITE, StandardOpenOption.READ)) {

            int startBlock = 1;
            int endBlock = chunks << 1;

            while (startBlock < endBlock) {

                int fs, ls;

                fs = targetRaf.read(ByteBuffer.wrap(byteBuffer, 0, bs), (startBlock - 1) * bs);
                int cb = startBlock;
                while (cb + 2 < endBlock) {
                    cb += 2;
                    ls = targetRaf.read(ByteBuffer.wrap(byteBuffer, fs, bs), (cb - 1) * bs);
                    innerSort.sort(byteBuffer, totalRead(fs, ls));
                    targetRaf.write(ByteBuffer.wrap(byteBuffer, fs, ls), (cb - 1) * bs);
                }
                targetRaf.write(ByteBuffer.wrap(byteBuffer, 0, fs), (startBlock - 1) * bs);

                fs = targetRaf.read(ByteBuffer.wrap(byteBuffer, bs, bs), (endBlock - 1) * bs);
                if (fs > 0) {
                    cb = endBlock;
                    while (cb - 2 > startBlock) {
                        cb -= 2;
                        ls = targetRaf.read(ByteBuffer.wrap(byteBuffer, 0, bs), (cb - 1) * bs);
                        innerSort.sort(byteBuffer, totalRead(fs, ls));
                        targetRaf.write(ByteBuffer.wrap(byteBuffer, 0, ls), (cb - 1) * bs);
                    }
                    targetRaf.write(ByteBuffer.wrap(byteBuffer, bs, fs), (endBlock - 1) * bs);
                }

                startBlock++;
                endBlock--;

                cb = startBlock;
                while (cb < endBlock) {
                    fs = targetRaf.read(ByteBuffer.wrap(byteBuffer, 0, bs), (cb - 1) * bs);
                    ls = targetRaf.read(ByteBuffer.wrap(byteBuffer, fs, bs), cb * bs);
                    innerSort.sort(byteBuffer, totalRead(fs, ls));
                    targetRaf.write(ByteBuffer.wrap(byteBuffer), (cb - 1) * bs);
                    cb += 2;
                }
            }
        }
    }

    private int totalRead(int r1, int r2) {
        return ((r1 == -1) ? 0 : r1) + ((r2 == -1) ? 0 : r2);
    }
}