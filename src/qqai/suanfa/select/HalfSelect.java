package qqai.suanfa.select;

/**
 * 二分查找
 *
 * @author qqai
 * @createTime 2020/12/15 22:35
 */
public class HalfSelect {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(halfSelect(arr, 19, 0, arr.length));
    }

    /*找到5在数组的那个下标 */
    public static int halfSelect(int[] arr, int num, int start, int end) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        while (start < end) {
            int mid = (start + end) >> 1;
            if (num < arr[mid]) {
                end = mid;
            } else if (arr[mid] < num) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
