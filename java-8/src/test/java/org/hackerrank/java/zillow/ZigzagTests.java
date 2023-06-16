package org.hackerrank.java.zillow;

import org.junit.Assert;
import org.junit.Test;

public class ZigzagTests {

    private final Solution solution = new Solution();

    @Test
    public void convertToZigzag() {
        Assert.assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
        Assert.assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
        Assert.assertEquals("A", solution.convert("A", 1));
        Assert.assertEquals("ABCDE", solution.convert("ABCDE", 1));
        Assert.assertEquals("A", solution.convert("A", 2));
    }

    class Solution {

        public String convert(String s, int numRows) {

            StringBuilder output = new StringBuilder();
            StringBuffer[] rows = new StringBuffer[numRows];

            int currentRow = 0;
            int delta = 1;
            boolean isSingleRow = (numRows == 1);

            for (int i = 0; i < s.length(); i++) {
                if (rows[currentRow] == null) {
                    rows[currentRow] = new StringBuffer();
                }
                char c = s.charAt(i);
                rows[currentRow].append(c);

                if(isSingleRow) continue;

                if(currentRow == 0 && delta == -1) {
                    delta = 1;
                } else if (currentRow == numRows - 1 && delta == 1){
                    delta = -1;
                }
                currentRow += delta;
            }

            for(int r = 0; r < numRows; r++){
                if (rows[r]!=null) {
                    output.append(rows[r]);
                }
            }

            return output.toString();
        }
    }
}
