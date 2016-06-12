package org.hackerrank.java.interview.jcp.futures;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);
    private final Thread thread = new Thread(future);

    public ProductInfo get()
            throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException){
                throw (DataLoadException) cause;
            } else {
                throw launderThrowable(cause);
            }
        }
    }

    private ProductInfo loadProductInfo() {
        return new ProductInfo("Preloader", "1.0.0-RELEASE");
    }

    public void start() {
        thread.start();
    }

    private InterruptedException launderThrowable(Throwable t){
        return new InterruptedException(t.getMessage());
    }

    public static void main(String... args) throws InterruptedException, DataLoadException {
        Preloader pl = new Preloader();
        //pl.start();
        ProductInfo pi = pl.get();
        System.out.println(pi.getName() + " " + pi.getVersion());
    }
}
