package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestRegExp {

    @Test
    public void test1() {
        Assert.assertEquals("rab", new RegExp().solution("(bar)"));
        Assert.assertEquals("foorabbazmilb", new RegExp().solution("foo(bar)baz(blim)"));
        Assert.assertEquals("foobazrabblim", new RegExp().solution("foo(bar(baz))blim"));
    }

    public class RegExp {
        
        String solution(String inputString) {
            StringBuffer answer = new StringBuffer();

            int i = 0;

            while(i < inputString.length()) {
                char c = inputString.charAt(i);
                if(c != '(') {
                    answer.append(c);
                } else {
                    i = reverse(inputString, i+1, answer);
                }
                i++;
            }


            return answer.toString();

        }

        private int reverse(String inputString, int startFrom, StringBuffer anwer) {
            StringBuffer sb = new StringBuffer();

            int i  = startFrom;

            char c = inputString.charAt(i);
            while(c != ')'){
                if(c != '('){
                    sb.append(c);
                } else {
                    i = reverse(inputString, i + 1, sb);
                }
                c = inputString.charAt(++i);
            }

            anwer.append(sb.reverse());

            return i;
        }
    }
}
