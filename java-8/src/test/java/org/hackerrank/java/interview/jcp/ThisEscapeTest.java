package org.hackerrank.java.interview.jcp;

import org.hackerrank.java.interview.jcp.confinement.EventSource;
import org.hackerrank.java.interview.jcp.confinement.ThisEscape;

public class ThisEscapeTest {
    public static void main(String[] args) {
        EventSource es = new EventSource();
        es.start();
        while(true) {
            new ThisEscape(es);
        }
    }
}