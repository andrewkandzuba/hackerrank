package org.hackerrank.java.interview.jcp.interruption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCancellationPoint {
    private final static Logger logger = LoggerFactory.getLogger(ThreadCancellationPoint.class);

    public static void main(String... args) {
        final AtomicInteger counter = new AtomicInteger();
        final ExecutorService service = Executors.newSingleThreadExecutor();

        service.submit(() -> {
           while (!Thread.currentThread().isInterrupted()){
               logger.info(String.format("counter = %s", counter.getAndIncrement()));
           }
        });

        service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.MILLISECONDS)) {
                service.shutdownNow();
                if (!service.awaitTermination(60, TimeUnit.MILLISECONDS)) {
                    logger.error("Pool did not terminate");
                }
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
