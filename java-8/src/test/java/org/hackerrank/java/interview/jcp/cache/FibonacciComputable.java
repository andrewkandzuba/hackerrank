package org.hackerrank.java.interview.jcp.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FibonacciComputable implements Computable<Integer, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(TestMemoizer.class);

    @Override
    public Integer compute(Integer arg) throws InterruptedException {
        logger.info("Compute: " + arg);
        if(arg == 0) return 1;
        if(arg == 1) return 1;
        return compute(arg - 1) + compute(arg - 2);
    }
}
