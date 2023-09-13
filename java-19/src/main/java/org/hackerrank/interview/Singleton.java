package org.hackerrank.interview;

import java.io.Serializable;

enum Singleton implements Serializable {
    INSTANCE("State Zero");

    private String state;

    private Singleton(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }
}