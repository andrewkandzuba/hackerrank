package org.hackerrank.java.interview.capitalone;

import org.junit.Assert;
import org.junit.Test;

public class TestIpAddressValidator {

    @Test
    public void test1() {
        Assert.assertTrue(new IpAddressValidator().solution("172.16.254.1"));
        Assert.assertTrue(new IpAddressValidator().solution("64.233.161.00"));
    }

    public class IpAddressValidator {
        boolean solution(String inputString) {
            String[] parts = inputString.split("\\.");
            if (parts.length != 4) return false;

            for (String p : parts) {
                if (p.length() == 0) return false;
                int b = 0;
                for (int i = 0; i < p.length(); i++) {
                    char c = p.charAt(i);
                    if (!Character.isDigit(p.charAt(i))) return false;
                    b = b * 10 + c - '0';
                }
                if (b > 255) return false;
            }
            return true;
        }
    }
}
