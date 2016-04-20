package org.hackerrank.java;

import java.util.*;

public class SolutionBalancedString {
    public static void main(String[] argh) {
        Set<Character> openParentheses = new HashSet<>(3);
        openParentheses.add('(');
        openParentheses.add('{');
        openParentheses.add('[');

        Set<Character> closingParentheses = new HashSet<>(3);
        closingParentheses.add(')');
        closingParentheses.add('}');
        closingParentheses.add(']');

        Map<Character, Character> parenthesesPairs = new HashMap<>();
        parenthesesPairs.put(')', '(');
        parenthesesPairs.put('}', '{');
        parenthesesPairs.put(']', '[');

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            Stack<Character> stack = new Stack<>();
            for (Character c : input.toCharArray()) {
                if (openParentheses.contains(c)) {
                    stack.push(c);
                }
                if (closingParentheses.contains(c)) {
                    if (stack.size() == 0) {
                        stack.push(c);
                    } else if (stack.peek().compareTo(parenthesesPairs.get(c)) == 0) {
                        stack.pop();
                    }
                }
            }
            System.out.println(stack.size() == 0 ? "true" : "false");
        }
    }
}
