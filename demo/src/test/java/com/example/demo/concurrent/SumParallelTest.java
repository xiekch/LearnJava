package com.example.demo.concurrent;

import org.junit.jupiter.api.Test;

public class SumParallelTest {
    @Test
    public void testSum1() throws InterruptedException {
        SumParallel sp = new SumParallel();
        int[] arr = new int[1000000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int piece = 200;
        long startTime1 = System.currentTimeMillis();
        System.out.println(sp.sum(arr, piece));
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1 - startTime1);

        long startTime2 = System.currentTimeMillis();
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        System.out.println(res);
        long endTime2 = System.currentTimeMillis();
        System.out.println(endTime2 - startTime2);
    }
}
