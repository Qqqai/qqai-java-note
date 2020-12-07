package qqai.suanfa.hash;

/**
 * @author qqai
 * @createTime 2020/12/5 10:25
 * @description：布隆过滤器
 */

public class BoolFilter {
    public static void main(String[] args) {
        int[] arr = new int[1000];

        // 30000是hash值
        int index = 30000;

        // 在int数组存放的位置
        int intIndex = index / 32; // 937

        // 在bit上应该在那个位置
        int bitIndex = index % 32;  // 16

        System.out.println(intIndex + "..." + bitIndex);

        /*
            1 << 16 = int : 0000 0000 0000 0000 1000 0000 0000 0000
            num = arr[intIndex];
            假设num = int : 0000 0000 0000 0101 0000 0000 0000 0000
            num | (1 << 16) = int : 0000 0000 0000 0101 1000 0000 0000
        */
        arr[intIndex] = arr[intIndex] | (1 << bitIndex);

    }
}
