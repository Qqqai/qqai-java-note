package qqai.synchronizedtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author qqai
 * @createTime 2020/10/27 23:30
 * @description：线程共享变量
 */

public class Mian3 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        t t1 = new t();
        t1.setList(list);
        t1.setName("A");
        t t2 = new t();
        t2.setName("B");
        t2.setList(list);
        t1.start();
        t2.start();
    }
}

class t extends Thread {
    private List<Integer> list;
    public static int count;

    public void setList(List<Integer> list) {
        this.list = list;
        count = list.size() - 1;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " -- > " + list.get(count--));

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

