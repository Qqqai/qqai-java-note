package qqai.bishi;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/10/10 18:57
 * @description：建信金融
 */

public class JianXinJinRongKeJi {
    public static void main(String[] args) {
        int[] ints = {54, 54532, 4324, 32432};
        int num = 43243;
        System.out.println(carsTrans(ints, num));
    }

    public static int carsTrans(int[] cars, int num) {
        Arrays.sort(cars);
        System.out.println(Arrays.toString(cars));
        int count = 0;
        if (cars.length == 0 || num == 0 || cars[cars.length - 1] == 0) {
            return -1;
        }
        while (true) {
            if (num <= 0) {
                return count;
            } else {
                num -= cars[cars.length - 1];
                count++;
            }
        }
    }
}
