package qqai.juc;

import java.util.concurrent.TimeUnit;

/**
 * 描述：线程调度测试
 *
 * @author qqai
 * @createTime 2020-09-07 20:34
 */

public class PhoneTest {

    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        //主线程休眠100毫秒
        Thread.sleep(100);

        new Thread(phone::sendSMS, "B").start();

        new Thread(phone::hello, "C").start();
    }

}

class Phone {
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("sendSMS");
    }

    public void hello() {
        System.out.println("hello");
    }
}