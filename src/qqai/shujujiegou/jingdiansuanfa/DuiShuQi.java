package qqai.shujujiegou.jingdiansuanfa;

import qqai.shujujiegou.jingdiansuanfa.sort.ChaRuPaiXu;

import java.util.Arrays;

/**
 * @author qqai
 * @createTime 2020/11/12 16:27
 * @description：对数器
 */

public class DuiShuQi {

    // 大样本测试
    public static void main(String[] args) {
        int testTime = 500000;
        int size = 10;
        int value = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(size, value);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);

            // 测试方法
            ChaRuPaiXu.sort(arr1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr3);
                break;
            }
        }
        System.out.println(succeed ? "Nice" : "Fucking fucked!");
    }

    private static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i : arr) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    // 绝对正确的排序方法
    public static int[] rightMethod(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public static int[] generateRandomArray(int size, int value) {
        // 生成随机长度的数组
        int[] arr = new int[(int) ((size + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((value + 1) * Math.random()) - (int) (value * Math.random());
        }
        return arr;
    }

    // 复制数组
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 判断数组是否相等
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr2 == null && arr1 != null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
