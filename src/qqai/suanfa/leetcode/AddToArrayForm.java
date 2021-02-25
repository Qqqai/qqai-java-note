package qqai.suanfa.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 989
 * by qqai
 * 2021/1/22 10:47
 */
public class AddToArrayForm {
    public static void main(String[] args) {
        System.out.println(addToArrayForm(new int[]{2, 1, 5}, 806));
    }

    public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        int decade = 0;
        for (int i = A.length - 1; i >= 0; i--, K /= 10) {
            int temp = K % 10 + A[i];
            if (decade == 1) {
                temp += 1;
                decade = 0;
            }
            if (temp >= 10) {
                list.add(temp % 10);
                decade = 1;
            } else
                list.add(temp);
        }
        if (decade == 1) {
            K += 1;
        }
        while (K > 0) {
            list.add(K % 10);
            K /= 10;
        }
        Collections.reverse(list);
        return list;
    }
}
