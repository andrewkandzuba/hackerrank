package org.hackerrank.java.interview.jcp.vehicles;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, ImmutablePoint> locations;

    public DelegatingVehicleTracker(Map<String, ImmutablePoint> points) {
        locations = new ConcurrentHashMap<>(points);
    }

    public Map<String, ImmutablePoint> getLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locations));
    }

    public ImmutablePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new ImmutablePoint(x, y)) == null) {
            throw new IllegalArgumentException(
                    "invalid vehicle name: " + id);
        }
    }
}
