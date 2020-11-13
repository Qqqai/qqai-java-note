package qqai.suanfa;

/**
 * @author qqai
 * @createTime 2020/11/12 23:40
 * @description：买汽水 有20块钱买汽水，1块一瓶，三盖子换一瓶，两空瓶换一瓶子，共能买多少汽水？
 */

public class MaiQiShui {
    public static void main(String[] args) {
        System.out.println(test(20, 20, 20));
    }

    public static int test(int sum, int pingzi, int gaizi) {
        if (pingzi < 2 && gaizi < 3) {
            return sum;
        }
        int newPingzi = pingzi / 2;
        int newGaizi = gaizi / 3;
        sum += newPingzi;
        sum += newGaizi;
        pingzi = newPingzi + newGaizi + pingzi % 2;
        gaizi = newGaizi + newPingzi + gaizi % 3;
        return test(sum, pingzi, gaizi);
    }
}
