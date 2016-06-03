package org.hackerrank.java.interview.jcp.synchronization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ListHelper<E>  {
    private final List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public void add(E e){
        synchronized (list){
           list.add(e);
        }
    }

    public void iterateAndAccept(Consumer<E> f){
        synchronized (list){
            for (E e : list) {
                f.accept(e);
            }
        }
    }

    public static void main(String... args) throws Exception {
        Random random = new Random();
        ListHelper<Integer> lh = new ListHelper<>();
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);

        try {
            service.scheduleAtFixedRate(() -> lh.add(random.nextInt(1024)), 10, 10, TimeUnit.MILLISECONDS);
            service.scheduleAtFixedRate(() -> lh.iterateAndAccept(System.out::println), 10, 10, TimeUnit.MILLISECONDS);

            Thread.sleep(10000);

            service.shutdown();
            service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            service.shutdownNow();
        }
    }
}
