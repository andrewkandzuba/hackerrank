package org.hackerrank.java.interview.jcp.futures;

public class ProductInfo {
    private final String name;
    private final String version;

    public ProductInfo(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
