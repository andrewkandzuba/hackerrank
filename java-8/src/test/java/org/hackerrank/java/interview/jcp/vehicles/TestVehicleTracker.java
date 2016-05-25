package org.hackerrank.java.interview.jcp.vehicles;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestVehicleTracker {
    private final static int MAX_AMOUNT_OF_VEHICLES = 10000;
    private final static Random randomizer = new Random();

    @Test
    public void testVehicleTracker() throws Exception {
        // init monitor
        Map<String, MutablePoint> locations = new HashMap<>();
        for(int i = 0; i < MAX_AMOUNT_OF_VEHICLES; i++){
            locations.put("vehicle-" + i, new MutablePoint());
        }
        MonitorVehicleTracker tracker = new MonitorVehicleTracker(locations);

        // schedule writers and render jobs
        ScheduledThreadPoolExecutor pool = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2);
        for(int i = 0; i < 10; i++){
            pool.scheduleAtFixedRate(() -> {
                tracker.setLocation("vehicle-" + randomizer.nextInt(MAX_AMOUNT_OF_VEHICLES), randomizer.nextInt(1024), randomizer.nextInt(1024));
            }, 10, 10, TimeUnit.MILLISECONDS);
        }
        pool.scheduleAtFixedRate(() -> {
            Map<String, MutablePoint> currentLocations = tracker.getLocations();
            for (String key : currentLocations.keySet()) {
                MutablePoint mp = currentLocations.get(key);
                System.out.println(String.format("The vehicle: %s is currently located at (%s, %s)", key, mp.x, mp.y));
            }
        }, 10, 20, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);

        try {
            pool.shutdown();
            pool.awaitTermination(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            pool.shutdownNow();
        }
    }
}
