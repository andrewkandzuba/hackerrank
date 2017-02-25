package org.hackerrank.java.interview.multiplication.table;

import org.junit.Test;

public class GeneratorTest {
    @Test
    public void generate() throws Exception {
        Generator generator = new Generator(10, 10);
        generator
                .count(30)
                .forEach(intPair -> System.out.println(String.format("%s * %s = [   ]\n", intPair.getR(), intPair.getC())));
    }
}
