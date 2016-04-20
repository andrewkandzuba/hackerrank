package org.hackerrank.java;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Pair {
    private String first;
    private String second;

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        return !(second != null ? !second.equals(pair.second) : pair.second != null);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}

public class SolutionStringPairs {
    public static void main(String[] args) {
        Set<Pair> pairs = new HashSet<>();
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while (testCases > 0) {
            pairs.add(new Pair(in.next(), in.next()));
            System.out.println(pairs.size());
        }
    }
}
