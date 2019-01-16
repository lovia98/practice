package com.example.study.algorithm.basic;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import org.junit.Test;

public class Prime {


    @Test
    public void isPrime_test() {

        assertEquals(false, isPrime(0));
        assertEquals(false, isPrime(1));
        assertEquals(true, isPrime(2));
        assertEquals(true, isPrime(3));
        assertEquals(false, isPrime(4));
        assertEquals(true, isPrime(5));
        assertEquals(true, isPrime(7));
        assertEquals(false, isPrime(9));
        assertEquals(false, isPrime(10));
    }

    private boolean isPrime(int number) {

        if (number < 2) {
            return false;
        }

        //0, 1, 2, 3
        int checkPrime[] = new int[number + 1];

        for (int i = 2; i <= number; i++) {

            if (checkPrime[i] == 1) {
                continue;
            }

            for (int j = i * i; j <= number; j += i) {
                checkPrime[j] = 1;
            }
        }

        return (checkPrime[number] == 0) ? true : false;
    }

    @Test
    public void test() {

        System.out.println(Math.sqrt(5));
    }
}
