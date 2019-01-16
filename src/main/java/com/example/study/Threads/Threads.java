package com.example.study.Threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;

public class Threads {

    private static void run() {
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void test() throws InterruptedException {

        Thread separateThread = new Thread(new ThreadPrinter());
        separateThread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("From the main thread: "
                + Thread.currentThread().getName());

            Thread.sleep(1000);
        }
    }

    private class ThreadPrinter implements Runnable {

        @Override
        public void run() {

            for (int i = 0; i < 5; i++) {
                System.out.println("From the new thread: "
                    + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Test
    public void excutorTest() {

        ExecutorService executors = Executors.newCachedThreadPool();

        executors.execute(new ThreadPrinter());
        executors.execute(new ThreadPrinter());
        executors.execute(new ThreadPrinter());
        executors.execute(Threads::run);
    }

    @Test
    public void callableTest() throws InterruptedException, ExecutionException, TimeoutException {

        ExecutorService executros = Executors.newCachedThreadPool();

        long startTiem = System.currentTimeMillis();

        Future<Double> future = executros.submit(new PiCalculator());

        double pi = future.get(10, TimeUnit.SECONDS);

        long stopTime = System.currentTimeMillis();

        System.out.printf("Calculated Pi in %d milliseconds: %10.9f%n", startTiem - stopTime, pi);

        executros.shutdown();
    }

    private class PiCalculator implements Callable {

        @Override
        public Double call() throws Exception {
            return 40D;
        }
    }

    @Test
    public void executorExample() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Runnable threadNamePrinter = new InfiniteThreaNamePrinter();

        System.out.println("Main thread:" +
            Thread.currentThread().getName());

        executor.execute(threadNamePrinter);
    }

    private class InfiniteThreaNamePrinter implements Runnable {

        @Override
        public void run() {

            while (true) {
                System.out.println("Run from thread: " +
                    Thread.currentThread().getName());
            }
        }
    }
}
