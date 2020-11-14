package qqai.mianshi;

import java.util.*;

/**
 * @author qqai
 * @createTime 2020/10/11 20:41
 * @description：58面试
 */

public class _58MainShi {

    public static int lineup(String peoples) {
        // write code here
        char[] array = peoples.toCharArray();
        int leftCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'B' && i > 0) {
                for (int j = 0; j < i; j++) {
                    if (array[j] == 'G') {
                        leftCount += (i - j);
                        array[j] = 'B';
                        array[i] = 'G';
                        break;
                    }
                }
            }
        }

        int rightCount = 0;
        char[] array1 = peoples.toCharArray();
        for (int i = array1.length - 1; i >= 0; i--) {
            if (array1[i] == 'B' && i < array1.length - 1) {
                for (int j = array1.length - 1; j > i; j--) {
                    if (array1[j] == 'G') {
                        rightCount += (j - i);
                        array1[j] = 'B';
                        array1[i] = 'G';
                        break;
                    }
                }
            }
        }
        return Math.min(leftCount, rightCount);
    }

    public static void main(String[] args) {
        String s = "GGGBBBGG";
        System.out.println(lineup(s));
//        int[] a = {1, 1, 1, 32, 43, 2, 4, 2, 4, 32, 423, 432, 4, 2, 4, 23, 42, 42, 2, 1};
//        System.out.println(Arrays.toString(removeDuplicate(a)));
    }

    public static int[] removeDuplicate(int[] array) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : array) {
            set.add(i);
        }
        int[] ints = new int[set.size()];
        int count = ints.length - 1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (set.contains(array[i])) {
                ints[count--] = array[i];
                set.remove(array[i]);
            }
        }
        return ints;
    }

}
