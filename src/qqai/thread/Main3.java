package qqai.thread;

/**
 * @author qqai
 * @createTime 2020/12/31 01:38
 */
public class Main3 {
    private static int i = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(i++);
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i++);
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i++);
        });
        // 启动顺序无所谓
        t1.start();
        t2.start();
        t3.start();
    }
}
