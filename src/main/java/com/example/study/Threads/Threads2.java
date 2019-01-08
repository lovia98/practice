package com.example.study.Threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class Threads2 {

    @Test
    public void waitToComplete() throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(1);

        executor.execute(new FiniteThreadNamePrinterLatch(latch));
        latch.await(5, TimeUnit.SECONDS);
    }

    private class FiniteThreadNamePrinterLatch implements Runnable {

        CountDownLatch latch;

        public FiniteThreadNamePrinterLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int i = 0; i < 25; i++) {
                System.out.println("Run from thread: " +
                    Thread.currentThread().getName());
            }
        }
    }

    @Test
    public void sameThread() {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };

        System.out.println("Main thread: " +
            Thread.currentThread().getName());

        executor.execute(new FiniteThreadNamePrinter());
    }

    private class FiniteThreadNamePrinter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 25; i++) {
                System.out.println("Run from thread: " +
                    Thread.currentThread().getName());
            }
        }
    }
}
