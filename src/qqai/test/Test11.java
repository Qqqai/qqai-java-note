package qqai.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * by qqai
 * 2021/1/20 18:10
 */
public class Test11 {
    private static volatile int s = 0;
    private static final ThreadPoolExecutor async = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<>());

    public static void main(String[] args) throws InterruptedException {
      /*  for (int i = 0; i < 10000; i++) {
            async.execute(() -> s++);
        }
        Thread.sleep(5000L);
        System.out.println(s);*/
        int[][] source = {{1, 2, 3, 4}, {1, 2, 3, 4}, {5, 6, 7, 8}, {2, 3, 4, 5}};
        int[] target = new int[source[0].length];
        System.arraycopy(source[0], 0, target, 0, 3);
        System.out.println(Arrays.toString(target));
    }
}
