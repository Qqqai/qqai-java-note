package qqai.suanfa.jinjie.recursion_and_dp;

/**
 * 汉诺塔
 *
 * @author qqai
 * @createTime 2020/12/15 15:21
 */
public class Process {

    public static void main(String[] args) {
        process(3, "左", "中", "右");
    }

    // 1-n 停留在from上  要全部挪到to上  大不能压住小的
    public static void process(int n, String from, String help, String to) {
        if (n == 1) {
            System.out.println("Move " + 1 + " from " + from + " to " + to);
            return;
        }
        process(n - 1, from, to, help);
        System.out.println("Move " + n + " from " + from + " to " + to);
        process(n - 1, help, from, to);
    }
}
