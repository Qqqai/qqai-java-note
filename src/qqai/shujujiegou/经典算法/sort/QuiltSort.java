package qqai.shujujiegou.经典算法.sort;

import java.util.Arrays;

/**
 * 描述：快速排序
 *
 * @author qqai
 * @createTime 2020-09-25 18:57
 */

public class QuiltSort {

    public static void main(String[] args) {
        int[] old = {5, 4, 6, 7, 354, 5644, 534, 5435, 534543754, 3, 3, 6, 4, 9, 6};
        for (int i = 0; i < old.length; i++) {
            int temp = i;
            int compare = old[i];
            for (int j = i + 1; j < old.length; j++) {
                if (compare > old[j]) {
                    temp = j;
                    compare = old[j];
                }
                new Thread().start();
            }
            if (temp != i) {
                int change = old[i];
                old[i] = old[temp];
                old[temp] = change;
            }
        }
        System.out.println(Arrays.toString(old));
    }

}
