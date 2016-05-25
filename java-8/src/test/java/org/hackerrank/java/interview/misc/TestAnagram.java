package org.hackerrank.java.interview.misc;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestAnagram {

    @Test
    public void testAnagrams() throws Exception {
        printAnagrams("cat");
        printAnagrams("caaat");
        printAnagrams("1234");
        printAnagrams("1");
        printAnagrams("");
    }

    @Test(expected = Exception.class)
    public void testBorderConditions() throws Exception {
        printAnagrams(null);
    }

    private void printAnagrams(String s) throws Exception {
        if(s == null){
            throw new Exception("Null parameter value is not allowed!!!");
        }
        System.out.printf("Input test data: %s%n", s);
        System.out.printf("Anagrams detected: %n");
        Set<String> anagrams = new HashSet<>();
        findAndPrint("", toUnique(s), anagrams);
        for(String anagram : anagrams){
            System.out.println(anagram);
        }
    }

    private void findAndPrint(String s, String remains, Set<String> anagrams) throws Exception {
        if(remains.length() == 0){
            if(anagrams.contains(s)){
                throw new Exception("Anagram repeating is detected!!!");
            }
            anagrams.add(s);
        } else {
            for (char c : remains.toCharArray()) {
                findAndPrint(s + c, remains.replace("" + c, ""), anagrams);
            }
        }
    }

    private String toUnique(String s) {
        StringBuilder sb = new StringBuilder();
        Set<Character> unique = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!unique.contains(c)) {
                unique.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
