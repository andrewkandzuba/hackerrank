package org.hackerrank.java.interview.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

public class TestLambda {

    private String prefix;

    @Test
    public void testCallLambda() {
        int wc = 0;
        Arrays.asList(1,2,3).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                //wc+=i;
            }
        });
    }

    private int v = 0;

    @Test
    public void testAdd() {
        int a = 1;
        int b = 2;
        add(a, b);
    }

    private void add(int a, int b){
        v =  a + b;
    }
}
