package org.hackerrank.java.interview.multiplication.table;

public class Application {

    public static void main(String[] args){
        if (args.length < 3)
            throw new IllegalStateException("Please provide table detentions of rows and columns plus nubmer of examples to generate");

        Generator generator = new Generator(
                Integer.parseInt(args[0]),
                Integer.parseInt(args[1]));
        generator
                .count(Integer.parseInt(args[2]))
                .forEach(intPair -> System.out.println(String.format("%s * %s = [   ]\n", intPair.getR(), intPair.getC())));

    }
}
