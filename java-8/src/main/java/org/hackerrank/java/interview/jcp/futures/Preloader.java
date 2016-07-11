package org.hackerrank.java.interview.jcp.futures;

import org.hackerrank.java.interview.jcp.interruption.concurrent.ExceptionManager;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {

    public static Preloader create(){
        Preloader pl = new Preloader();
        pl.start();
        return pl;
    }

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
                throw ExceptionManager.launderThrowable(cause);
            }
        }
    }

    private ProductInfo loadProductInfo() {
        return new ProductInfo("Preloader", "1.0.0-RELEASE");
    }

    public void start() {
        thread.start();
    }

    public static void main(String... args) throws InterruptedException, DataLoadException {
        ProductInfo pi = Preloader.create().get();
        System.out.println(pi.getName() + " " + pi.getVersion());
    }
}
