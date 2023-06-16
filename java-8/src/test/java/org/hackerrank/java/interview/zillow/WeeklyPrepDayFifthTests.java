package org.hackerrank.java.interview.zillow;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class WeeklyPrepDayFifthTests {

    public static void noPrefix(List<String> words) {
        // Write your code here
        Trie trie = new Trie();
        for (String w : words)
            if (!trie.valid(w)) {
                System.out.printf("BAD SET%n%s%n", w);
                return;
            }
        System.out.println("GOOD SET");
    }

    @Test
    public void testTrie() {
        List<String> words = new ArrayList<>();
        words.add("aabghgh");
        words.add("aab");
        noPrefix(words);
        List<String> words1 = new ArrayList<>();
        words1.add("aab");
        words1.add("aabghgh");
        noPrefix(words1);
        List<String> words2 = new ArrayList<>();
        words2.add("abc");
        words2.add("aee");
        noPrefix(words2);
    }

    private static class Queue {
        private final Stack<Integer> in = new Stack<>();
        private final Stack<Integer> out = new Stack<>();

        public void push(Integer e) {
            in.push(e);
        }

        public Integer pop() {
            Integer e = peek();
            if (e != null) {
                out.pop();
            }
            return e;
        }

        public void printFront() {
            Integer e = peek();
            System.out.println(e);
        }

        private Integer peek() {
            if (out.empty()) {
                transfer();
            }
            return out.peek();
        }

        private void transfer() {
            while (in.size() > 0) {
                out.push(in.pop());
            }
        }

    }

    static class TrieNode {
        final char data;
        final Map<Character, TrieNode> children = new HashMap<>();

        boolean isEnd = false;

        TrieNode(char data) {
            this.data = data;
        }
    }

    static class Trie {
        TrieNode root = new TrieNode('*');

        boolean valid(String s) {
            Map<Character, TrieNode> children = root.children;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                TrieNode n = children.get(c);
                if (n == null) {
                    n = new TrieNode(c);
                    children.put(c, n);
                }
                if (i <= s.length() - 1) {
                    if (n.isEnd) {
                        return false;
                    } else if (i == s.length() - 1) {
                        if (n.children.size() > 0) {
                            return false;
                        }
                        n.isEnd = true;
                    }
                }
                children = n.children;
            }

            return true;
        }
    }

    @Test
    public void testMissingNumbers() {
        missingNumbers(
                List.of(203, 204,205,206,207,208,203,204,205,206),
                List.of(203,204,204,205,206,207,205,208,203,206,205,206,204))  ;
    }

    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        TreeSet<Integer> missed = new TreeSet<>();
        Map<Integer, Object> m =new HashMap<>();
        m.computeIfAbsent(1, i -> new Object());
        Set<Integer> shared = new HashSet<>(arr);
        for(Integer b : brr){
            if(!shared.contains(b)){
                missed.add(b);
            }
        }
        return new ArrayList<>(missed);
    }

    @Test
    public void testPairs() {
        pairs(2, List.of(1,5,3,4,2));
    }

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        int[] sorted = new int[arr.size()];
        Arrays.sort(sorted);

        int count = 0;
        int i = 0;
        int j = 1;
        int n = sorted.length;

        while(j<n) {
            int diff = sorted[j] - sorted[i];
            if(diff == k){
                count++;
                j++;
            } else if (diff > k) {
                i++;
            } else if (diff < k) {
                j++;
            }
        }
        return count;

    }

    @Test
    public void testPivot() {
        balancedSums(List.of(1));
    }

    public static String balancedSums(List<Integer> arr) {
        // Write your code here
        boolean doesPivotExit = false;
        int n = arr.size();
        if(n==1) {
            doesPivotExit = true;
        } else if(n >= 3) {
            int pivotIdx = (arr.size() - 1) / 2;
            int sumL = 0;
            int sumR = 0;
            for(int i = 0; i < pivotIdx; i++) {
                sumL += arr.get(i);
            }
            for(int i = pivotIdx + 1; i < n; i++) {
                sumR += arr.get(i);
            }
            while(pivotIdx > 0 || pivotIdx < n - 1){
                if(sumL == sumR) {
                    doesPivotExit = true;
                    break;
                }
                int pivot = arr.get(pivotIdx);
                if (sumL < sumR) {
                    if(sumL + pivot > sumR){
                        break;
                    } else {
                        sumL += pivot;
                        sumR -= arr.get(++pivotIdx);
                    }
                }
                if (sumL > sumR) {
                    if(sumR + pivot > sumL){
                        break;
                    } else {
                        sumL -= arr.get(--pivotIdx);
                        sumR += pivot;
                    }
                }
            }
        }
        return doesPivotExit ? "YES" : "NO";
    }

    @Test
    public void maxSum() {
        System.out.println(maximumSum(List.of(3L, 3L, 9L, 9L, 5L), 7));
    }

    public static long maximumSum(List<Long> a, long m) {
        // Write your code here
        int n = a.size();
        long[] X = new long[n];
        for(int i = 0; i < n; i++){
            X[i] = a.get(i);
        }
        long max = findMaxSubarraySum(X, 0, n - 1, m);
        return max;
    }

    static long maxCrossingSum(long [] X, int l, int mid, int r, long m) {
        long sum = 0;
        long maxLeftSum = Long.MIN_VALUE;
        for (int i = mid; i >= l; i = i - 1)
        {
            sum = (sum + X[i]) % m;
            if (sum > maxLeftSum)
                maxLeftSum = sum;
        }
        sum = 0;
        long maxRightSum = Long.MIN_VALUE;
        for (int i = mid + 1; i <= r; i = i + 1)
        {
            sum = (sum + X[i]) % m;
            if (sum % m > maxRightSum % m)
                maxRightSum = sum;
        }
        return (maxLeftSum + maxRightSum);
    }

    static long findMaxSubarraySum(long[] X, int l, int r, long m) {
        if (l == r)
            return X[l];
        else {
            int mid = l + (r - l) / 2;
            long leftMaxSum = findMaxSubarraySum(X, l, mid, m);
            long rightMaxSum = findMaxSubarraySum(X, mid + 1, r, m);
            long crossingMaxSum = maxCrossingSum(X, l, mid, r, m);
            return Math.max(Math.max(leftMaxSum % m, rightMaxSum % m), crossingMaxSum % m);
        }
    }


    @Test
    public void palindromeTest() {
        Assert.assertTrue(isPalindrome("aaab", 0, 2));
        Assert.assertEquals(3, palindromeIndex("aaab"));
    }

    public static int palindromeIndex(String s) {
        // Write your code here
        if(s.length() == 1){
            return -1;
        }

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if(s.charAt(i) == s.charAt(j)){
                i++;
                j--;
            } else {
                if(isPalindrome(s, i+1, j)){
                    return i;
                }
                if (isPalindrome(s, i, j-1)){
                    return j;
                }
                return -1;
            }
        }

        return -1;
    }

    private static boolean isPalindrome(String s, int i, int j){
        while (i < j) {
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;
    }

    @Test
    public void lengthOfLongestSubstringTest() {
        lengthOfLongestSubstring(" ");
    }

    public int lengthOfLongestSubstring(String s) {
        int max_so_far = 0;
        int max_unrepeatable_len = 0;
        int size = s.length();
        Set<Character> repeats = new HashSet<>();
        int start = 0;

        while(start < size) {
            for(int i = start; i < size; i++){
                Character c = s.charAt(i);
                if(!repeats.contains(s.charAt(i))){
                    max_so_far++;
                    repeats.add(c);
                } else {
                    if(max_so_far > max_unrepeatable_len){
                        max_unrepeatable_len = max_so_far;
                    }
                    break;
                }
            }
            max_so_far = 0;
            repeats.clear();
            start++;
        }
        return max_unrepeatable_len;
    }
}

