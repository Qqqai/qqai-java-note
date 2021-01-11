package qqai.thread;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author qqai
 * @createTime 2020/12/30 23:38
 */
public class Main2 extends Thread {
    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {
        final Main2 test = new Main2();

        Runnable waitTask = () -> {
            try {
                test.shouldGo();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main2.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            System.out.println(Thread.currentThread().getName() + " finished Execution");
        };

        Thread t1 = new Thread(waitTask, "qq-1"); //will wait
        Thread t2 = new Thread(waitTask, "qq-2"); //will wait
        Thread t3 = new Thread(waitTask, "qq-3"); //will wait
        Thread t4 = new Thread(() -> {
            test.go();
            System.out.println(Thread.currentThread() + " finished Execution");
        }, "qq-4"); //will notify

        //starting all waiting thread
        t1.start();
        t2.start();
        t3.start();

        //pause to ensure all waiting thread started successfully
        Thread.sleep(200);

        //starting notifying thread
        t4.start();

    }

    private synchronized void shouldGo() throws InterruptedException {
        while (!go) {
            System.out.println(Thread.currentThread().getName()
                    + " is going to wait on this object");
            wait(); //release lock and reacquires on wakeup
            System.out.println(Thread.currentThread().getName() + " is woken up");
        }
        go = false; //resetting condition
    }

    /*
     * both shouldGo() and go() are locked on current object referenced by "this" keyword
     */
    private synchronized void go() {
        while (!go) {
            System.out.println(Thread.currentThread().getName()
                    + " is going to notify all or one thread waiting on this object");
            go = true; //making condition true for waiting thread
           // notify(); // only one out of three waiting thread qq-1, qq-2,qq-3 will woke up
            notifyAll(); // all waiting thread  qq-1, qq-2,qq-3 will woke up,
        }
    }
}
