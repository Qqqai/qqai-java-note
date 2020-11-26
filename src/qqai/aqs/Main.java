package qqai.aqs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：AQS学习
 *
 * @author qqai
 * @createTime 2020-09-24 20:44
 */

public class Main {
    private Lock lock = new ReentrantLock();
//    new CountDownLatch

    public void test() {
        lock.lock();
        lock.unlock();
    }

    public static void main(String[] args) {
        
    }
}
