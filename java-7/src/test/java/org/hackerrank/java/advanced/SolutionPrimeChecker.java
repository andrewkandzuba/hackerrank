package org.hackerrank.java.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.lang.System.in;

class Prime {
    private HashMap<Integer, Integer> primes = new HashMap<>();

    public void checkPrime(int... nums) {
        StringBuilder sb = new StringBuilder();
        for(int n : nums){
            if(primes.containsKey(n) || isPrime(n)){
                if(sb.length() > 0){
                    sb.append(" ");
                }
                sb.append(n);
            }
        }
        System.out.println(sb);
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        } else if (n <= 3) {
            primes.put(n, n);
            return true;
        } else if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i = +6;
        }
        primes.put(n, n);
        return true;
    }
}

public class SolutionPrimeChecker {

    //private static InputStream in = System.in;

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int n1 = Integer.parseInt(br.readLine());
            int n2 = Integer.parseInt(br.readLine());
            int n3 = Integer.parseInt(br.readLine());
            int n4 = Integer.parseInt(br.readLine());
            int n5 = Integer.parseInt(br.readLine());
            Prime ob = new Prime();
            ob.checkPrime(n1);
            ob.checkPrime(n1, n2);
            ob.checkPrime(n1, n2, n3);
            ob.checkPrime(n1, n2, n3, n4, n5);
            Method[] methods = Prime.class.getDeclaredMethods();
            Set<String> set = new HashSet<>();
            boolean overload = false;
            for (Method method : methods) {
                if (set.contains(method.getName())) {
                    overload = true;
                    break;
                }
                set.add(method.getName());

            }
            if (overload) {
                throw new Exception("Overloading not allowed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
