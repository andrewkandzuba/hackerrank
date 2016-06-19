package org.hackerrank.java.interview.jcp.cache;


import java.util.logging.Logger;

public class FibonacciComputable implements Computable<Integer, Integer> {
    private final static Logger logger = Logger.getLogger("FibonacciComputable");

    @Override
    public Integer compute(Integer arg) throws InterruptedException {
        logger.info("Compute: " + arg);
        if(arg == 0) return 1;
        if(arg == 1) return 1;
        return compute(arg - 1) + compute(arg - 2);
    }
}
