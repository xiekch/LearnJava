package com.example.demo.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class SumParallel {
    private ExecutorService executor;

    public SumParallel() {
        executor = Executors.newCachedThreadPool();
    }

    public long sum(int[] arr, int piece) throws InterruptedException {
        int length = arr.length;
        if (length <= 0) {
            return 0;
        }
        if (piece <= 0) {
            piece = 1;
        }
        if (length < piece) {
            piece = length;
        }
        AtomicLong res = new AtomicLong();
        CountDownLatch latch = new CountDownLatch(piece);

        int step = length / piece;
        for (int i = 0; i < length; i += step) {
            // executor.execute(new SumCommand(arr, i, i + step > arr.length ? arr.length
            //         : i + step, res, latch));
            executor.execute(SumCommand.obtain(arr, i, i + step > arr.length ? arr.length
                    : i + step, res, latch));
        }

        latch.await();
        return res.get();
    }

    /**
     * use object pool to reuse SumCommands
     */
    static class SumCommand implements Runnable {
        public static final Object sPoolSync = new Object();
        private static SumCommand sPool;
        private static final int MAX_POOL_SIZE = 50;
        private static int sPoolSize = 0;

        private int[] arr;
        private int start;
        private int end;
        private SumCommand next;
        private AtomicLong res;
        private CountDownLatch latch;

        public SumCommand(int[] arr, int start, int end, AtomicLong res, CountDownLatch latch) {
            this.arr = arr;
            this.start = start;
            this.end = end;
            this.res = res;
            this.latch = latch;
        }

        public static SumCommand obtain(int[] arr, int start, int end, AtomicLong res, CountDownLatch latch) {
            synchronized (sPoolSync) {
                if (sPool != null) {
                    SumCommand s = sPool;
                    s.arr = arr;
                    s.start = start;
                    s.end = end;
                    s.latch = latch;
                    sPool = sPool.next;
                    sPoolSize--;

                    return s;
                }
            }
            return new SumCommand(arr, start, end, res, latch);
        }

        public void recycle() {
            synchronized (sPoolSync) {
                if (sPoolSize < MAX_POOL_SIZE) {
                    next = sPool;
                    sPool = this;
                    sPoolSize++;
                }
            }
        }

        @Override
        public void run() {
            if (arr == null || latch == null) {
                return;
            }

            long delta = 0;
            for (int i = start; i < end; i++) {
                delta += arr[i];
            }
            res.getAndAdd(delta);

            latch.countDown();
            recycle();
        }
    }
}
