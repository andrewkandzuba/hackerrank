package org.hackerrank.java.interview.pl.logic;

class FizzBuzzBang {

    /**
     * Return Splat, Fizz, Buzz, or Bang depending on number
     * <p>
     * Based on old English game of FizzBuzz.
     * Aim - read this code; I want to see all test cases,
     * ideas for refactoring
     * and convert to a function or functor for a functional langugage.
     *
     * @param number
     * @return
     */
    public static String getFizzBuzzBang(Integer number) {
        boolean divisibleByThree = 0 == (number % 3);
        boolean divisibleByFive = 0 == (number % 5);
        boolean divisibleBySeven = 0 == (number % 7);
        StringBuilder sb = new StringBuilder();
        if(divisibleByThree){
            sb.append("Fizz");
        }
        if(divisibleByFive){
            sb.append("Buzz");
        }
        if(divisibleBySeven) {
            sb.append("Bang");
        }
        return sb.length() > 0 ? sb.toString() : "Splat";
    }


    public static void main(String[] argc) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getFizzBuzzBang(i));
        }
    }
}

