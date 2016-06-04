package org.hackerrank.java.interview.jcp.synchronization;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HolderContainer {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }

    public static void main(String... args) throws Exception {
        HolderContainer container = new HolderContainer();
        ScheduledThreadPoolExecutor service =
                new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);

        for(int i = 0; i < 10; i++) {
            service.schedule(() -> {
                container.initialize();
                System.out.println("Initialized!");
            }, 10, TimeUnit.MILLISECONDS);
            service.schedule(() -> {
                if(container.holder != null) {
                    container.holder.assertSanity();
                    System.out.println("Verified!");
                }
            }, 10, TimeUnit.MILLISECONDS);
        }

        service.shutdown();
        service.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }

}
