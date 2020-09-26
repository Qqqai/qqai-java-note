package qqai.shujujiegou.经典算法.sort;

import java.util.Arrays;

/**
 * 描述：冒泡排序
 *
 * @author qqai
 * @createTiie 2020-09-25 20:49
 */

public class MaoPaoSort {

    public static void main(String[] args) {
        int[] old = {5, 4, 6, 7, 354, 5644, 534, 5435, 534543754, 3, 3, 6, 4, 9, 6};
        for (int i = 0; i < old.length - 1; i++) {
            for (int h = 0; h < old.length - i - 1; h++) {
                if (old[h] > old[h + 1]) {
                    int temp = old[h];
                    old[h] = old[h + 1];
                    old[h + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(old));
    }
}
