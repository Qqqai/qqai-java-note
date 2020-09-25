package qqai.sort;

import java.util.Arrays;

/**
 * 描述：直接插入排序
 *
 * @author qqai
 * @createTime 2020-09-25 15:49
 */

public class ChaRuPaiXu {
    public static void main(String[] args) {
        int[] old = {3, 3, 6, 4, 9, 6};
        for (int i = 1; i < old.length; i++) {
            old = sort(old, i);
        }
        System.out.println(Arrays.toString(old));
    }

    private static int[] sort(int[] old, int i) {
        for (int j = i - 1; j >= 0; j--) {
            if (old[j] >= old[i]) {
                int temp = old[i];
                old[i] = old[j];
                old[j] = temp;
                i = j;
            } else {
                break;
            }
        }
        return old;
    }
}
