package com.example.study.Threads;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.Test;

public class Threads3 {

    @Test
    public void sharedState() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        SimpleCounter c = new SimpleCounter();
        executorService.execute(new CounterSetter(c));

        c.setNumber(200);
        assertEquals(200, c.getNumber());
    }

    private class SimpleCounter {

        private int number = 0;

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }

    private class CounterSetter implements Runnable {

        private SimpleCounter counter;

        public CounterSetter(SimpleCounter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {

            while (true) {
                counter.setNumber(100);
            }
        }
    }
}
