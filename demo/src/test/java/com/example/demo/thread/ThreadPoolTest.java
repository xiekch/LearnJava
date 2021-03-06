package com.example.demo.thread;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ThreadPoolTest {
    @Test
    void testThreadPool() {
        ThreadPoolExecutor threadPooleExecutor = new ThreadPoolExecutor(3, 5, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        assertNotNull(threadPooleExecutor);
        for (int i = 0; i < 30; i++) {
            final int j = i;
            threadPooleExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("name:%s this is %d", Thread.currentThread().getName(), j));
                }
            });
        }
    }
}
