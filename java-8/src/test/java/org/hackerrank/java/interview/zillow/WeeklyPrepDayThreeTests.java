package org.hackerrank.java.interview.zillow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WeeklyPrepDayThreeTests {

    private static final int EN_ALPHABET_SIZE = 26;

    @Test
    public void findEvenDivisorsOfTest() {
        int k = 2;
        String s = "middle-Outz";

        char[] chipherLowerCase = new char[EN_ALPHABET_SIZE];
        char[] chipherUpperCase = new char[EN_ALPHABET_SIZE];

        char c = (char)((byte)'a'+ k % EN_ALPHABET_SIZE);

        for(int i = 0; i < EN_ALPHABET_SIZE; i++) {
            chipherLowerCase[i] = c;
            chipherUpperCase[i] = Character.toUpperCase(c);
            if(c == 'z'){
                c = 'a';
            } else {
                c = (char)((byte)c + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char sc : s.toCharArray()) {
            char ec = sc;
            if (Character.isAlphabetic(sc)){
                ec = Character.isUpperCase(sc) ?
                        chipherUpperCase[(byte)sc-'A'] :
                        chipherLowerCase[(byte)sc-'a'];
            }
            sb.append(ec);
        }

        String es = sb.toString();

        int i = 2 | 1;
    }

    private static List<Integer> findEvenDivisorsOf(int x) {
        List<Integer> result = new ArrayList<>();
        for(int y = 1; y < x; y++){
            if( x % y == 0) result.add(y);
        }
        return result;
    }



}
