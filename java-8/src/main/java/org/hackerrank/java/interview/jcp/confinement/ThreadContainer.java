package org.hackerrank.java.interview.jcp.confinement;

public class ThreadContainer {
    private boolean initialized;

    public ThreadContainer() throws InterruptedException {
        new Thread(new InnerThread()).start();
        Thread.sleep(1000L);
        initialized = true;
    }

    private class InnerThread implements Runnable {
        @Override
        public void run() {
            if(!initialized){
                System.out.println("Initialization of ThreadContainer is not completed yet!");
            }
        }
    }

    public static void main(String... args) throws Exception {
        new ThreadContainer();
    }
}
